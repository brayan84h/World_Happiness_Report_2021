package App;

import Utils.Country;
import Utils.Region;

public class Main {

    public static void main(String[] args) {

    Region ADMIN = new Region("ADMIN");

    ADMIN.ReadCVS();

    ADMIN.Show_Countries();
    ADMIN.ShowRegions();


    //Cree estos objetos para Verificar si los metodos funcionaban como debian de funcionar
        Region Western_Europe = new Region("Western Europe");
        Country Spain = new Country("Spain" , "Western Europe" , 0.932 , 74.700 , 0.761 , -0.081 , 0.745);
        Country Italy = new Country("Italy" , "Western Europe" , 0.880 , 73.800 , 0.693 , -0.084 , 0.866);
        Country Cyprus = new Country("Cyprus" , "Western Europe" , 0.802 , 73.898 , 0.763 , -0.015 , 0.844);
        Country Portugal = new Country("Portugal" , "Western Europe" , 0.879 , 72.600 , 0.892 , -0.244 , 0.887);

        Western_Europe.Add_country_of_region(Spain);
        Western_Europe.Add_country_of_region(Italy);
        Western_Europe.Add_country_of_region(Cyprus);
        Western_Europe.Add_country_of_region(Portugal);

        Western_Europe.Calculations_of_region();

        Western_Europe.start();
    }
}
