package program;

import java.util.Vector;

public class ServiceLocSpec {
    public Vector<Integer> locuriDispLoja = new Vector<Integer>(50);
    public Vector<Integer> locuriDispRand = new Vector<Integer>(50);

    public ServiceLocSpec(){

    }

    public ServiceLocSpec(Vector<Integer> locuriDispLoja, Vector<Integer> locuriDispRand) {
        this.locuriDispLoja = locuriDispLoja;
        this.locuriDispRand = locuriDispRand;
    }

    public Vector<Integer> getLocuriDispLoja() {
        return locuriDispLoja;
    }

    public void setLocuriDispLoja(Vector<Integer> locuriDispLoja) {
        this.locuriDispLoja = locuriDispLoja;
    }

    public Vector<Integer> getLocuriDispRand() {
        return locuriDispRand;
    }

    public void setLocuriDispRand(Vector<Integer> locuriDispRand) {
        this.locuriDispRand = locuriDispRand;
    }

    @Override
    public String toString() {
        return "ServiceLocSpec{" +
                "locuriDispLoja=" + locuriDispLoja +
                ", locuriDispRand=" + locuriDispRand +
                '}';
    }

    public void locruri_Disponibile(Integer id_spectacol){
        this.locuriDispRand.clear();
        this.locuriDispLoja.clear();
        RepoLoc locuri = new RepoLoc();
        locuri.addDb();
        RepoRezervare rezervari = new RepoRezervare();
        rezervari.addBd();
        for(int i=0; i< locuri.size();i++){
            Loc loc = locuri.getElement(i);
            Integer id_l = loc.getId();
            if (loc.getTip().equals("loja")){
                //System.out.println(id_l);
                this.locuriDispLoja.add(id_l);
            }
            else {
                this.locuriDispRand.add(id_l);
                //System.out.println(id_l);
            }
        }
        for (int i=0; i< this.locuriDispLoja.size(); i++) {
            //System.out.println(id_l);
            for (int j = 0; j < rezervari.size(); j++) {
                if (rezervari.getRezervari().get(j).getId_spectacol().equals(id_spectacol)) {
                    Vector<Integer> locuri_r = rezervari.getRezervari().get(j).getLocuri();
                    if (locuri_r.contains(this.locuriDispLoja.get(i))) {
                        this.locuriDispLoja.remove(i);
                    }
                }
            }
        }
        for (int i=0; i< this.locuriDispRand.size(); i++) {
            for (int j = 0; j < rezervari.size(); j++) {
                if (rezervari.getRezervari().get(j).getId_spectacol().equals(id_spectacol)) {
                    Vector<Integer> locuri_r = rezervari.getRezervari().get(j).getLocuri();
                    if (locuri_r.contains(this.locuriDispRand.get(i))) {
                        this.locuriDispRand.remove(i);
                    }
                }
            }
        }
      //  System.out.println("Locuri Loja" + this.locuriDispLoja.size());
        //System.out.println("Locuri Rand" + this.locuriDispRand.size());

    }
    public Vector<SpecLoc> listareLoc(){
        Vector<SpecLoc> sp = new Vector<SpecLoc>(30);
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        repoSpectacol.addDb();
        Integer n=repoSpectacol.size();
        RepoLoc repoLoc = new RepoLoc();
        repoLoc.addDb();
        for (int i=0; i<n;i++){
            String nume = repoSpectacol.getSpectacole().get(i).getNume();
            Integer id_s = repoSpectacol.getSpectacole().get(i).getId();
            locruri_Disponibile(id_s);
            String pret_l = repoSpectacol.getPretLId(id_s);
            String pret_r = repoSpectacol.getPretRId(id_s);
            String data = repoSpectacol.getDataId(id_s);
            String ora = repoSpectacol.getOraId(id_s);
            //System.out.println("Spectacol "+ i+ " " + this.locuriDispRand.size());
            for (int j=0;j<locuriDispRand.size();j++){
                Loc loc = repoLoc.getElId(locuriDispRand.get(j));
                Integer numar = loc.getNumar();
                String tip = loc.getTip();
                SpecLoc spec = new SpecLoc(nume,data,ora, numar, tip,pret_r);
                sp.add(spec);
            }
            for (int j=0;j<locuriDispLoja.size();j++){
                Loc loc = repoLoc.getElId(locuriDispLoja.get(j));
                Integer numar = loc.getNumar();
                String tip = loc.getTip();
                SpecLoc spec = new SpecLoc(nume,data,ora, numar, tip,pret_l);
                sp.add(spec);
            }

        }
        return  sp;
    }
    public Vector<SpecLoc> locuriIstoric(String cnp){
        Vector<SpecLoc> vector =new Vector<SpecLoc>(100);
        RepoRezervare repoRezervare = new RepoRezervare();repoRezervare.addBd();
        RepoSpectacol repoSpectacol = new RepoSpectacol(); repoSpectacol.addDb();
        RepoLoc repoLoc = new RepoLoc(); repoLoc.addDb();
        for (int i=0; i<repoRezervare.size();i++){
            Rezervare rp =repoRezervare.getRezervari().get(i);
            if(rp.getId_client().equals(cnp)){
                String nume = repoSpectacol.getNumeID(repoRezervare.getRezervari().get(i).getId_spectacol());
                String pret_l = repoSpectacol.getPretLId(repoRezervare.getRezervari().get(i).getId_spectacol());
                String pret_r = repoSpectacol.getPretRId(repoRezervare.getRezervari().get(i).getId_spectacol());
                String data = repoSpectacol.getDataId(repoRezervare.getRezervari().get(i).getId_spectacol());
                String ora = repoSpectacol.getOraId(repoRezervare.getRezervari().get(i).getId_spectacol());
                String pr = null;
                Vector<Integer> locuri = rp.getLocuri();
                for (int j=0; j<locuri.size();j++){
                    Loc loc = repoLoc.getElId(locuri.get(j));
                    Integer numar = loc.getNumar();
                    String tip = loc.getTip();
                    if (tip.equals("loja")) {
                         pr = pret_l;
                    } else {
                         pr=  pret_r;
                    }
                    SpecLoc sploc = new SpecLoc(nume,data, ora, numar, tip, pr);
                    vector.add(sploc);
                    }
                }

            }
        return vector;
    }
    public boolean writeDB(Spectacol spectacol){
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        return repoSpectacol.writeBD(spectacol);
    }
    public boolean removeBD(Integer id){
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        return repoSpectacol.stergerDB(id);
    }
    public boolean modificaData(Integer id, String data){
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        return repoSpectacol.modifData(id, data);
    }
    public boolean modificaOra(Integer id, String data){
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        return repoSpectacol.modifOra(id, data);
    }
    public boolean modificaPretl(Integer id, String data){
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        return repoSpectacol.modifPretl(id, data);
    }
    public boolean modificaPretr(Integer id, String data){
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        return repoSpectacol.modifPretr(id, data);
    }
    public String  insertClient(Client c){
        RepoClient repoClient = new RepoClient();
        return repoClient.insertClient(c);
    }
    public String getParola(String c){
        RepoClient repoClient = new RepoClient();
        return repoClient.getPassCnp(c);
    }
    public boolean addReze(String cnp, Integer loc, Integer sp){
        RepoRezervare repoRezervare = new RepoRezervare();
        return repoRezervare.adaugareRezervare(cnp,loc, sp);
    }
    public boolean anulareRez(String cnp, Integer loc, Integer sp){
        RepoRezervare repoRezervare = new RepoRezervare();
        return repoRezervare.anulareRez(cnp,loc, sp);
    }
}

