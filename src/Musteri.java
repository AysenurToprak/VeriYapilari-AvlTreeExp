public class Musteri implements Comparable<Musteri>{

    int musteriID;
    String musteriAdi;
    int uyelikDurumu;
    int cuzdanDurumu;
    int aylikHarcama;
    int yorumSayisi;
    int urunCesidi;
    int islemTuru;
    int oncelikPuani;

    public Musteri(int musteriID, String musteriAdi, int uyelikDurumu, int cuzdanDurumu, int aylikHarcama, int yorumSayisi, int urunCesidi, int islemTuru) {
        this.musteriID = musteriID;
        this.musteriAdi = musteriAdi;
        this.uyelikDurumu = uyelikDurumu;
        this.cuzdanDurumu = cuzdanDurumu;
        this.aylikHarcama = aylikHarcama;
        this.yorumSayisi = yorumSayisi;
        this.urunCesidi = urunCesidi;
        this.islemTuru = islemTuru;
    }

    public Musteri() {

    }

    public int getMusteriID() {
        return musteriID;
    }

    public void setMusteriID(int musteriID) {
        this.musteriID = musteriID;
    }

    public String getMusteriAdi() {
        return musteriAdi;
    }

    public void setMusteriAdi(String musteriAdi) {
        this.musteriAdi = musteriAdi;
    }

    public int getUyelikDurumu() {
        return uyelikDurumu;
    }

    public void setUyelikDurumu(int uyelikDurumu) {
        this.uyelikDurumu = uyelikDurumu;
    }

    public int getCuzdanDurumu() {
        return cuzdanDurumu;
    }

    public void setCuzdanDurumu(int cuzdanDurumu) {
        this.cuzdanDurumu = cuzdanDurumu;
    }

    public int getAylikHarcama() {
        return aylikHarcama;
    }

    public void setAylikHarcama(int aylikHarcama) {
        this.aylikHarcama = aylikHarcama;
    }

    public int getYorumSayisi() {
        return yorumSayisi;
    }

    public void setYorumSayisi(int yorumSayisi) {
        this.yorumSayisi = yorumSayisi;
    }

    public int getUrunCesidi() {
        return urunCesidi;
    }

    public void setUrunCesidi(int urunCesidi) {
        this.urunCesidi = urunCesidi;
    }

    public int getIslemTuru() {
        return islemTuru;
    }

    public void setIslemTuru(int islemTuru) {
        this.islemTuru = islemTuru;
    }

    public int getOncelikPuani() {
        return oncelikPuani;
    }

    public void setOncelikPuani(int oncelikPuani) {
        this.oncelikPuani = oncelikPuani;
    }

    public int compareTo(Musteri other) {
        return Integer.compare(this.oncelikPuani, other.oncelikPuani);
    }
}
