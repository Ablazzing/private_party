package org.javaacademy.private_party.service;

import org.javaacademy.private_party.entity.Guest;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GuestRepository {
    public void save(Connection connection, Guest guest) {
        String sql = """
                    insert into guest (name, email, phone) values (?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, guest.getName());
            preparedStatement.setString(2, guest.getEmail());
            preparedStatement.setString(3, guest.getPhone());
            int countInsertedRows = preparedStatement.executeUpdate();
            if (countInsertedRows != 1) {
                throw new RuntimeException("Не вставлен гость в таблицу");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllNames(Connection connection) {
        String sql = """
                    select gu."name" from guest_name gu
                """;

        try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            List<String> names = new ArrayList<>();
            while (resultSet.next()) {
                    names.add(resultSet.getString(1));
            }
            return names;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
