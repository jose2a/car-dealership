package com.revature.cardealership.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.cardealership.model.Dealership;
import com.revature.cardealership.utils.LoggingUtil;

public class DealershipDAOImpl implements DealershipDAO {

	private String fileName;
	private Dealership dealership;

	public DealershipDAOImpl(String fileName, String dealershipName) {
		this.fileName = fileName;
		
		LoggingUtil.trace("Create a new dealership in case no file is found");
		
		this.dealership = new Dealership(dealershipName);
	}

	@Override
	public boolean loadDealership() throws IOException {
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis);) {

			this.dealership = (Dealership) ois.readObject();
			
			LoggingUtil.trace("Read dealership from file");

			return true;
		} catch (FileNotFoundException e) {
			this.save();
			
			LoggingUtil.trace("File does not exist. Trying to create a new one.");

			LoggingUtil.debug(e.getMessage());
		} catch (ClassNotFoundException e) {
			LoggingUtil.debug(e.getMessage());
		} catch (IOException e) {
			LoggingUtil.debug(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean save() {
		try (FileOutputStream fos = new FileOutputStream(this.fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {

			oos.writeObject(this.dealership);
			
			LoggingUtil.trace("Wrote dealership to file");

			return true;

		} catch (FileNotFoundException e) {
			LoggingUtil.error(e.getMessage());
		} catch (IOException e) {
			LoggingUtil.error(e.getMessage());
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
