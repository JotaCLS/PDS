



public class veiculosMain {

    public  static void main(String[] args){


        Empresa empresa1 = new Empresa("Univ aveiro", 3810076, "ua@ua.pt");
        System.out.println(empresa1);

        Motociclo moto1 = new Motociclo("78-JF-21", "Kymco", "vitalitty", 50, "desportivo");

        System.out.println(moto1);

        AutomovelLigeiro ligeiro1 = new AutomovelLigeiro("12-AB-34", "Audi", "R8", 100, "123", 5);

        System.out.println(ligeiro1);

        Taxi taxi1 = new Taxi("56-CD-78", "toyoya", "corolla", 80, "678", 5, "89349403");

        System.out.println(taxi1);

        PesadoDeMercadoria pesado1 = new PesadoDeMercadoria("91-EF-11", "volvo", "xc40", 500, "987", 10000, 500000);

        System.out.println(pesado1);

        PesadoDeMercadoria passageiros1 = new PesadoDeMercadoria("12-GH-13", "ford", "D20", 400, "321", 5000, 20000);

        System.out.println(passageiros1);

        empresa1.adicionarVeiculo(moto1);
        empresa1.adicionarVeiculo(ligeiro1);
        empresa1.adicionarVeiculo(taxi1);            //testando adicionar veiculos ja criados e tambem criar novos veiculos ao adicionar
        empresa1.adicionarVeiculo(pesado1);
        empresa1.adicionarVeiculo(passageiros1);
        empresa1.adicionarVeiculo(new Motociclo("78-JF-22", "Kymco", "vitalitty", 50, "estrada"));

        System.out.println(ligeiro1.distanciaTotal());

        ligeiro1.trajeto(10);// veiculo "ligeiro1" realiza trajeto de 10km
        ligeiro1.trajeto(50); // veiculo "ligeiro1" realiza trajeto de 50km

        System.out.println(ligeiro1.distanciaTotal());
        System.out.println(ligeiro1.ultimoTrajeto() + "\n");

        System.out.println(empresa1.conjuntoViaturas.get(0).equals(moto1));
        System.out.println(empresa1.conjuntoViaturas.get(0).equals(empresa1.conjuntoViaturas.get(1)));

        AutomovelLigeiroElet LigeiroElet1 = new AutomovelLigeiroElet("98-ZZ-76", "mercedes", "GLA", 150, "1234", 5);
        System.out.println(LigeiroElet1); // informações do veiculo eletrico antes do trajeto
        LigeiroElet1.trajeto(50); // veiculo eletrico realiza trajeto de 50km
        System.out.println(LigeiroElet1); // informações do veiculos apos o trajeto

        PesadoDePassageirosElet PassageirosElet = new PesadoDePassageirosElet("00-AA-11", "BMW", "z2", 200, "1234", 5000, 40);
        System.out.println(PassageirosElet);
        PassageirosElet.trajeto(200);
        System.out.println(PassageirosElet);

        LigeiroElet1.carregar(100); //carregamento 
        PassageirosElet.carregar(50);

        System.out.println(LigeiroElet1.autonomia); // informações da autonomia apos carregamento
        System.out.println(PassageirosElet.autonomia);








        
    }




    


    
}
