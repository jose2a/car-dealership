package com.revature.cardealership.ui.screens;

import java.io.IOException;
import java.util.Iterator;

import com.revature.cardealership.model.Car;
import com.revature.cardealership.services.CarService;
import com.revature.cardealership.utils.ServiceUtil;

public class ListCarScreen implements Screen {
	
	private CarService carService;
	
	public ListCarScreen() throws IOException {
		this.carService = ServiceUtil.getCarService();
	}

	@Override
	public void display() {	    
		System.out.println("--------- CARS ---------");
		
		Iterator<Car> carIterator = carService.getCars().iterator();
		
		while (carIterator.hasNext()) {
			Car car = carIterator.next();
			System.out.println(car.toSingleLineString());
		}

	}

}
