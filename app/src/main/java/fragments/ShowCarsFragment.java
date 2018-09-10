package fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.cars.cars.CarImage;
import com.cars.cars.R;

public class ShowCarsFragment extends Fragment implements SearchView.OnQueryTextListener {
    private ImageView imageView;

    public ShowCarsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_car_image, container, false);

        imageView = view.findViewById(R.id.image_car);

        showCar();

        return view;
    }

    private void showCar() {
        CarImage carActivity = (CarImage) getActivity();
        String carName = carActivity.getCarName();

        switch (carName){
            case "AlfaRomeo":
                imageView.setImageResource(R.drawable.alfa_romeo);
                break;
            case "Mercedes":
                imageView.setImageResource(R.drawable.mercedes);
                break;
            case "Opel":
                imageView.setImageResource(R.drawable.opel);
                break;
            case "Ferrari":
                imageView.setImageResource(R.drawable.ferrari);
                break;
            case "Maserati":
                imageView.setImageResource(R.drawable.maserati);
                break;
            case "Audi":
                imageView.setImageResource(R.drawable.audi);
                break;
            case "Renault":
                imageView.setImageResource(R.drawable.renault);
            case "Bmw":
                imageView.setImageResource(R.drawable.bmw);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
