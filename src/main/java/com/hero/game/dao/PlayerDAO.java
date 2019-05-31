package com.hero.game.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hero.game.model.Player;
import com.hero.game.model.Spaceship;

public class PlayerDAO {
	Connection dbConnection;

	public PlayerDAO(Connection db) {
		this.dbConnection = db;
	}

	public List<Player> getPlayers() {
		String sql = "Select * from players";
		try {

			ResultSet resultSet = dbConnection.createStatement().executeQuery(sql);
			List<Player> playersfromDB = new ArrayList<Player>();
			while (resultSet.next()) {

				Spaceship chartData = null;
				try {
					ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(resultSet.getBytes("spaceship")));
					chartData = (Spaceship) in.readObject();
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				playersfromDB.add(new Player(resultSet.getString("name"), resultSet.getInt("level"),
						resultSet.getInt("stones"), resultSet.getInt("health"), chartData));
			}
			return playersfromDB;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void persistPlayer(Player player) {
		String sql = "insert into players(name,level,stones,health,spaceship) values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setString(1, player.getName());
			pstmt.setInt(2, player.getStones());
			pstmt.setInt(3, player.getLevel());
			pstmt.setInt(4, player.getHealth());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(player.getSpaceship());
			pstmt.setBinaryStream(5, new ByteArrayInputStream(baos.toByteArray()));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updatePlayer(Player player) {
		String sql = "update players set stones=?,health=?,spaceship=?,level=? where name=?";
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setInt(1, player.getStones());
			pstmt.setInt(2, player.getHealth());
			pstmt.setInt(4, player.getLevel());
			pstmt.setString(5, player.getName());

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(player.getSpaceship());
			pstmt.setBinaryStream(3, new ByteArrayInputStream(baos.toByteArray()));
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePlayer(String playerName) {
		String sql = "delete from players where name=?";
		try {
			PreparedStatement pstmt = dbConnection.prepareStatement(sql);
			pstmt.setString(1, playerName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
