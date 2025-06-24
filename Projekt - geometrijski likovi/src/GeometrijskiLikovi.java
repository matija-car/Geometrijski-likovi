public class GeometrijskiLikovi {

    public static abstract class GeometrijskiLik {
        private String ime;

        public GeometrijskiLik(String ime) {
            this.ime = ime;
        }

        public String getIme() {
            return ime;
        }

        public double getOpseg() {
            return 0;
        }

        public double getPovrsina() {
            return 0;
        }


        public boolean sadrziTocku(int x, int y) {
            return false;
        }


        public void crtaj(Slika slika) {
            int visina = slika.getVisina();
            int sirina = slika.getSirina();

            for (int y = 0; y < visina; y++) {
                for (int x = 0; x < sirina; x++) {
                    if (this.sadrziTocku(x, y)) {
                        slika.upaliElement(x, y);
                    }
                }
            }
        }
    }

    public static class Pravokutnik extends GeometrijskiLik {
        private int x;
        private int y;
        private int sirina;
        private int visina;

        public Pravokutnik(String ime, int x, int y, int sirina, int visina) {
            super(ime);
            this.x = x;
            this.y = y;
            this.sirina = sirina;
            this.visina = visina;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSirina() {
            return sirina;
        }

        public int getVisina() {
            return visina;
        }

        @Override
        public double getPovrsina() {
            return sirina * visina;
        }

        @Override
        public double getOpseg() {
            return 2 * (sirina + visina);
        }

        @Override
        public boolean sadrziTocku(int x, int y) {
            return (x >= this.x && x < this.x + sirina) && (y >= this.y && y < this.y + visina);
        }


        @Override
        public void crtaj(Slika slika) {

            if (x + sirina <= 0 || x >= slika.getSirina()) return;
            if (y + visina <= 0 || y >= slika.getVisina()) return;

            int odX = Math.max(0, x);
            int doX = Math.min(x + sirina, slika.getSirina());
            int odY = Math.max(0, y);
            int doY = Math.min(y + visina, slika.getVisina());

            for (int yy = odY; yy < doY; yy++) {
                for (int xx = odX; xx < doX; xx++) {
                    slika.upaliElement(xx, yy);
                }
            }
        }

        @Override
        public String toString() {
            return "Pravokutnik " + getIme() + " na (" + x + ", " + y + "), širina: " + sirina + ", visina: " + visina;
        }
    }

    public static class Krug extends GeometrijskiLik {
        private int cx;
        private int cy;
        private int r;

        public Krug(String ime, int cx, int cy, int r) {
            super(ime);
            this.cx = cx;
            this.cy = cy;
            this.r = r;
        }

        public int getCx() {
            return cx;
        }

        public int getCy() {
            return cy;
        }

        public int getR() {
            return r;
        }

        @Override
        public double getOpseg() {
            return 2 * r * Math.PI;
        }

        @Override
        public double getPovrsina() {
            return r * r * Math.PI;
        }

        @Override
        public boolean sadrziTocku(int x, int y) {
            int dx = x - cx;
            int dy = y - cy;
            return dx * dx + dy * dy <= r * r;
        }

        @Override
        public String toString() {
            return "Krug " + getIme() + " centar (" + cx + ", " + cy + "), radijus: " + r;
        }
    }

    public static class Slika {
        private int sirina;
        private int visina;
        private boolean[][] elementi;

        public Slika(int sirina, int visina) {
            this.sirina = sirina;
            this.visina = visina;
            elementi = new boolean[visina][sirina];
        }

        public int getSirina() {
            return sirina;
        }

        public int getVisina() {
            return visina;
        }

        public void upaliElement(int x, int y) {
            elementi[y][x] = true;
        }

        public void ugasiElement(int x, int y) {
            elementi[y][x] = false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder((sirina + 1) * visina);
            for (int y = 0; y < visina; y++) {
                for (int x = 0; x < sirina; x++) {
                    sb.append(elementi[y][x] ? '*' : '.');
                }
                sb.append('\n');
            }
            return sb.toString();
        }
    }

    public static class Trokut extends GeometrijskiLik{
        private int x;
        private int y;
        private int visina;
        private int stranica;

        public Trokut(String ime,int x,int y, int visina, int stranica){
            super(ime);
            this.x = x;
            this.y = y;
            this.visina = visina;
            this.stranica = stranica;
        }

        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        public int getVisina(){
            return visina;
        }
        public int getStranica(){
            return stranica;
        }

        @Override
        public double getPovrsina() {
            return (stranica*stranica*1.73)/4;
        }

        @Override
        public double getOpseg() {
            return 3*stranica;
        }

        @Override
        public boolean sadrziTocku(int x, int y) {
            int dubina = y - this.y;
            if (dubina < 0 || dubina >= visina) return false;

            int polubaza = dubina;
            int centar = this.x;

            int lijevo = centar - polubaza;
            int desno = centar + polubaza;

            return x >= lijevo && x <= desno;
    }
    }
    
    

    public static class Lajna extends GeometrijskiLik{
        private int x;
        private int y;
        private int duljina;

        public Lajna(String ime,int x,int y, int duljina){
            super(ime);
            this.x = x;
            this.y = y;
            this.duljina = duljina;
        }

        int getX(){return x;}
        int getY(){return y;}
        int getDuljina(){return duljina;}

        @Override
        public boolean sadrziTocku(int x,int y){
            return (x>this.x && x<this.x+duljina-1 && y==this.y);
        }


    }

    public static void main(String[] args) {
        Slika slika = new Slika(60, 20);

        Pravokutnik pravokutnik = new Pravokutnik("pravokutnik", 5, 5,5, 5);
        Krug krug2 = new Krug("krug", 50, 10, 2);
        Trokut trokut = new Trokut("trokut", 9, 9, 10, 5);
        Lajna lajna = new Lajna("lajna", 5, 3, 50);


        pravokutnik.crtaj(slika);
        krug2.crtaj(slika);
        trokut.crtaj(slika);
        lajna.crtaj(slika);

        System.out.println(slika.toString());
    }
}
