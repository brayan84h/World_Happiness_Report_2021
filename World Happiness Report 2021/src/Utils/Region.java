package Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Region extends Thread{

    private String name_Region;
    ArrayList<Country> countries_of_region;
    ArrayList<Country> countries;
    ArrayList<String> regions;
    ArrayList<Region> regionsArrayList = new ArrayList<Region>();

    public Region(String name_Region) {
        setName_Region(name_Region);
        this.countries_of_region = new ArrayList<Country>();
        this.countries = new ArrayList<Country>();
        this.regions = new ArrayList<String>();
    }

    public String getName_Region() {
        return name_Region;
    }

    public void setName_Region(String name_Region) {
        this.name_Region = name_Region;
    }


    public void ReadCVS(){
        System.out.println("\n----DATOS LEIDOS EN EL ARCHIVO CVS----\n");
        String path = "D:\\Proyectos\\ProjectFinal\\src\\Utils\\world-happiness-report-2021.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            boolean header = true;
            while((line = br.readLine()) != null){
                if (header) {
                    header = false; //Este If se hizo para excluir la lectura del encabezado del CVS
                    continue;
                }
                String[] values = line.split(","); // Esta linea se ve un split el cual es para definir cual es el separador de las columnas en el CVS
                countries.add(new Country(values[0], values[1], Double.parseDouble(values[7]), Double.parseDouble(values[8]),
                        Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11])));
                AddRegion(values[1]);
                System.out.println(values[0] + " , " + values[1] + " , " + values[7] + " , " + values[8] + " , " + values[9] + " , " + values[10] + " , " + values[11]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Esta fuuncion muestra los Paises leidos en el Archivo CSV
    public void Show_Countries(){
        System.out.println("\n\t----MOSTRANDO LOS PAISES----");
        int cont = 1;
        for (Country c:countries){
            System.out.println("  "+cont+". "+c.getName_country());
            cont++;
        }
    }

    // Muestra las regiones
    public void ShowRegions(){
        System.out.println("\n\t----MOSTRANDO LAS REGIONES----");
        for (String c: regions) {
            System.out.println("  -"+c);
        }
    }

    // Valida que no se agreguen regiones Repetidas
    public void AddRegion(String region){
        if (!regions.contains(region)) {
            regions.add(region);
        }
        Convert_Strings_to_Regions(regions);
    }

    //Esta funcion Convertira el ArrayList de String a tipo Region
    public void Convert_Strings_to_Regions(ArrayList<String> list){
        for (String s: list) {
            regionsArrayList.add(new Region(s));
        }
    }

    public void ShowREGIONS(){
        for (Region r: regionsArrayList) {
            System.out.println(" -"+r.getName_Region());
        }
    }

    //En esta funcion hacemos el llamado de las funciones las cuales hacen los calculos por la region
    public void Calculations_of_region(){
        System.out.println("\n");
        System.out.println("---- Los calculos de la region "+this.getName_Region()+"  ----");
        System.out.println("El Promedio de Social Support es: "+Prom_Social_Support());
        System.out.println("El Promedio de Healthy life expectancy es: "+Healthy_life_expectancy());
        System.out.println("El Promedio de Freedom to make life choices es: "+Fredom_to_make_choises());
        System.out.println("El Promedio de Generosity es: "+Generosity());
        System.out.println("El Promedio de Perception of corruption es: "+Perception_of_corruption());
        Country_with_the_highest_PC();
        Country_with_the_highest_G();
        System.out.println("\n");
    }

    public double Prom_Social_Support(){
        double Prom_SS;
        double sum_SS=0;
        for (Country c: countries_of_region) {
            sum_SS = c.getSocialSupport();
        }
        Prom_SS = (sum_SS/countries_of_region.size());
        return Prom_SS;
    }


    public double Healthy_life_expectancy(){
        double Prom_HLE;
        double sum_HLE=0;
        for (Country c: countries_of_region) {
            sum_HLE = c.getHealthy_life_expectancy();
        }
        Prom_HLE = (sum_HLE/countries_of_region.size());
        return Prom_HLE;
    }

    public double Fredom_to_make_choises(){
        double Prom_FMLC;
        double sum_FMLC=0;
        for (Country c: countries_of_region) {
            sum_FMLC = c.getFredom_to_make_choises();
        }
        Prom_FMLC = (sum_FMLC/countries_of_region.size());
        return Prom_FMLC;
    }

    public double Generosity(){
        double Prom_G;
        double sum_G=0;
        for (Country c: countries_of_region) {
            sum_G = c.getGenerosity();
        }
        Prom_G = (sum_G/countries_of_region.size());
        return Prom_G;
    }

    public double Perception_of_corruption(){
        double Prom_PC;
        double sum_PC=0;
        for (Country c: countries_of_region) {
            sum_PC = c.getPerception_of_corruption();
        }
        Prom_PC = (sum_PC/countries_of_region.size());
        return Prom_PC;
    }

    public void Country_with_the_highest_PC(){
        double max = 0;
        String name_country = "";
        for (Country c: countries_of_region){
            if (c.getPerception_of_corruption()>max) {
                max = c.getPerception_of_corruption();
                name_country = c.getName_country();
            }
        }
        System.out.println("El pais con mayor percepcion de corrupcion es: "+ name_country+ " con su #"+max);
    }

    public void Country_with_the_highest_G(){
        double max = 0;
        String name_country = "";
        for (Country c: countries_of_region){
            if (c.getGenerosity()>max) {
                max = c.getGenerosity();
                name_country = c.getName_country();
            }
        }
        System.out.println("El pais con la mas alta generosidad es: "+ name_country + " con su #"+max);
    }

    //Funcion para agregar páises a la region
    public void Add_country_of_region(Country country){
        this.countries_of_region.add(country);
    }

    public void CreateCSV(){
        PrintCSV(getName_Region());
    }

    public void PrintCSV(String name_file){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("D:\\Proyectos\\ProjectFinal\\src\\"+name_file+".csv"), StandardCharsets.UTF_8));

            //Esta es la linea Principal osea el Header
            bufferedWriter.write("Country name,Regional indicator,Social support,Healthy life expectancy,Freedom to make life choices,Generosity,Perceptions of corruption\n");

            for (Country c: countries_of_region) {
                StringBuffer line = new StringBuffer(c.toString());
                if (c.getRegional_indicate().equalsIgnoreCase(name_file)) {
                    line.append(c.getName_country());
                    line.append(",");
                    line.append(c.getRegional_indicate());
                    line.append(",");
                    line.append(c.getSocialSupport()-Prom_Social_Support());
                    line.append(",");
                    line.append(c.getHealthy_life_expectancy()-Healthy_life_expectancy());
                    line.append(",");
                    line.append(c.getFredom_to_make_choises()-Fredom_to_make_choises());
                    line.append(",");
                    line.append(c.getGenerosity()-Generosity());
                    line.append(",");
                    line.append(c.getPerception_of_corruption()-Perception_of_corruption());

                    bufferedWriter(line.toString());
                    bufferedWriter.newLine();

                }
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void bufferedWriter(String toString) {
    }


    //IMPLEMENTACION DE LOS HILOS PARA LA CREACION DE EL ARCHIVO Csv
    @Override
    public void run() {
        CreateCSV();
    }
}
