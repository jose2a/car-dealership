package com.revature.cardealership.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.cardealership.model.Dealership;

public class DealershipDAOImpl implements DealershipDAO {

	private static final Logger LOGGER = LogManager.getLogger(CarDAOImpl.class.getName());

	private String fileName;
	private Dealership dealership;

	public DealershipDAOImpl(String fileName) {
		this.fileName = fileName;
		this.dealership = null;
	}

	@Override
	public boolean loadDealership() {
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis);) {

			this.dealership = (Dealership) ois.readObject();

			return true;
		} catch (FileNotFoundException e) {
			LOGGER.debug("File for the dealership does not exist.");
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean save() {
		try (FileOutputStream fos = new FileOutputStream(this.fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {

			oos.writeObject(this.dealership);

			return true;

		} catch (FileNotFoundException e) {
			LOGGER.debug("File not found.");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return false;
	}

	@Override
	public Dealership getDealership() {
		return this.dealership;
	}

	@Override
	public void setDealership(Dealership dealership) {
		this.dealership = dealership;

	}

}
