package org.javaacademy.private_party.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.private_party.dto.GuestDtoRq;
import org.javaacademy.private_party.entity.Guest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {
    @Value("${app.database.url}")
    private String databaseUrl;
    private final GuestRepository guestRepository;

    public void addGuest(GuestDtoRq dtoRq, String username, String password) {
        Guest guest = convertToEntity(dtoRq);
        Connection connection = createConnection(username, password);
        try {
            guestRepository.save(connection, guest);
        } finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection createConnection(String username, String password) {
        try {
            return DriverManager.getConnection(databaseUrl, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Guest convertToEntity(GuestDtoRq dtoRq) {
        Guest guest = new Guest();
        guest.setEmail(dtoRq.getEmail());
        guest.setName(dtoRq.getName());
        guest.setPhone(dtoRq.getPhone());
        return guest;
    }

    public List<String> getNames(String username, String password) {
        Connection connection = createConnection(username, password);
        try {
           return guestRepository.getAllNames(connection);
        } finally {
            closeConnection(connection);
        }
    }
}
