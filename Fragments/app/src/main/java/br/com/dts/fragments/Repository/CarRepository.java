package br.com.dts.fragments.Repository;

import java.util.ArrayList;
import java.util.List;

import br.com.dts.fragments.Car;

/**
 * Created by diegosouza on 8/5/16.
 */
public class CarRepository {

    private static CarRepository sInstance;

    private List<Car> mCarList;

    private CarRepository(){
        init();
    }

    public static CarRepository getInstance(){
        if (sInstance == null) {
            sInstance = new CarRepository();
        }
        return sInstance;
    }

    private void init(){
        mCarList = new ArrayList<Car>();
        mCarList.add(new Car("VW", "Gol", "KKK-2222", "Prata", 2009));
        mCarList.add(new Car("VW", "Golf", "KKK-2332", "Prata", 2010));
        mCarList.add(new Car("Fiat", "Mobi", "KKK-5622", "Azul", 2016));
        mCarList.add(new Car("BMW", "320i", "KKK-6772", "Prata", 2010));
        mCarList.add(new Car("GM", "Celta", "KKK-2992", "Verde", 2023));
        mCarList.add(new Car("Ford", "Fiesta", "KKK-3922", "Branca", 2009));
    }

    public List<Car> getCarList(){
        return mCarList;
    }

    public Car getCarInPosition(int pos){
        return mCarList.get(pos);
    }
}
