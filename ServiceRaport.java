package program;

import java.util.Vector;

public class ServiceRaport {
    public Vector<Raport> raport = new Vector<Raport>(20);

    public ServiceRaport(Vector<Raport> raport) {
        this.raport = raport;
    }
    public ServiceRaport(){

    }

    public Vector<Raport> getRaport() {
        return raport;
    }

    public void setRaport(Vector<Raport> raport) {
        this.raport = raport;
    }
    public Integer size(){
        return this.size();
    }
    public void add(Raport raport){
        this.add(raport);
    }
    public Vector<Raport> raportSpectacol(){
        Vector<Raport> raports = new Vector<Raport>(30);
        RepoLoc repoLoc = new RepoLoc();repoLoc.addDb();
        RepoSpectacol repoSpectacol = new RepoSpectacol(); repoSpectacol.addDb();
        for (int i=0; i<repoSpectacol.getSpectacole().size();i++){
            Integer id = repoSpectacol.getSpectacole().get(i).getId();
            String nume=  repoSpectacol.getSpectacole().get(i).getNume();
            String data=  repoSpectacol.getSpectacole().get(i).getData();
            String ora=  repoSpectacol.getSpectacole().get(i).getOra();
            ServiceLocSpec service = new ServiceLocSpec();
            service.locruri_Disponibile(repoSpectacol.getSpectacole().get(i).getId());
            Integer n1 = service.getLocuriDispLoja().size();
            Integer n2 = service.getLocuriDispRand().size();
            Integer loc_l= n1+n2;
            Integer loc_o  = repoLoc.size() - loc_l;
            String pre_l=  repoSpectacol.getSpectacole().get(i).getPret_l();
            String pre_r=  repoSpectacol.getSpectacole().get(i).getPret_r();
            Raport rap = new Raport(id,nume, data, ora, loc_l, loc_o, pre_l, pre_r);
            raports.add(rap);
        }
        return raports;
    }
}
