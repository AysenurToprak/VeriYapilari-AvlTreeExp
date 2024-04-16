import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static PriorityQueue<Musteri> musteriKuyrugu;
    static Temsilci[] temsilciler;
    static int gorusmeSayac;
    Gorusme[] gorusmeler;
    static List<Musteri> musteriler = new ArrayList<>();
    static AVLTree gorusmeAgaci = new AVLTree();
    public static int oncelikPuaniHesapla(Musteri musteri) {
        int oncelikP = 0;

        if (musteri.uyelikDurumu == 1) {
            oncelikP += 10;
        }
        if (musteri.cuzdanDurumu == 1) {
            oncelikP += 15;
        }
        if (musteri.aylikHarcama > 100 && musteri.aylikHarcama < 999) {
            oncelikP += 5;
        } else if (musteri.aylikHarcama > 1000 && musteri.aylikHarcama < 3999) {
            oncelikP += 10;
        } else if (musteri.aylikHarcama > 4000) {
            oncelikP += 15;
        }
        if (musteri.urunCesidi >= 2 && musteri.urunCesidi <= 5) {
            oncelikP += 5;
        } else if (musteri.urunCesidi >= 6 && musteri.urunCesidi <= 10) {
            oncelikP += 10;
        } else if (musteri.urunCesidi > 10) {
            oncelikP += 15;
        }
        if (musteri.yorumSayisi >= 2 && musteri.yorumSayisi <= 7) {
            oncelikP += 5;
        } else if (musteri.yorumSayisi >= 8) {
            oncelikP += 10;
        }

        return oncelikP;
    }

    public static int gorusmeSuresiHesapla(Musteri musteri, int islemTuru){
        int islemSuresi = 0;
        switch (islemTuru){
            case 1 :
                islemSuresi += 3;
                break;
            case 2:
                islemSuresi +=8;
                break;
            case 3:
                islemSuresi += 7;
                break;
            case 4:
                islemSuresi +=6;
                break;
            case 5:
                islemSuresi +=4;
                break;
        }
        int oncelikPuani = oncelikPuaniHesapla(musteri);
        if (oncelikPuani >= 15 && oncelikPuani <= 40) {
            islemSuresi += 3;
        } else if (oncelikPuani > 40) {
            islemSuresi += 5;
        }

        return islemSuresi;
    }
    public static void musteriKayitEkle(Musteri musteri) {
        musteriKuyrugu.add(musteri);
    }
    public static void gorusmeYap() {
        if (musteriKuyrugu == null) {
            musteriKuyrugu = new PriorityQueue<>();
        }

        Random rand = new Random();
        long currentTime = System.currentTimeMillis();

        for (int i = 0; i < temsilciler.length; i++) {
            Temsilci temsilci = temsilciler[i];
            while (!musteriKuyrugu.isEmpty()) {
                Musteri musteri = musteriKuyrugu.poll();

                int islemTuru = musteri.islemTuru;
                int gorusmeSuresi = gorusmeSuresiHesapla(musteri, islemTuru);
                int oncelikpuani = oncelikPuaniHesapla(musteri);
                long baslamaTime = currentTime + rand.nextInt(10) * 24 * 60 * 60 * 1000;
                Date baslamaDate = new Date(baslamaTime);
                String gorBaslama = baslamaDate.toString();

                long bitisTime = baslamaTime + gorusmeSuresi * 60 * 1000;
                Date bitisDate = new Date(bitisTime);
                String gorBitis = bitisDate.toString();

                Gorusme gorusme = new Gorusme(++gorusmeSayac, musteri.musteriID, temsilci.temsilciID, gorusmeSuresi, gorBaslama, gorBitis);
                gorusmeAgaci.insert(gorusme);

                System.out.println("Müşteri: " + musteri.musteriID + ", Oncelik Puanı: " + oncelikpuani + ", Görüşme Süresi: " + gorusmeSuresi + " dakika, Başlama Tarihi: " + gorBaslama + ", Bitiş Tarihi: " + gorBitis);
            }
        }
    }

    public static void gorusmeListele() {
        System.out.println("Görüşmelerin Listesi:");
        gorusmeAgaci.inOrderTraversal();
    }
    public static void main(String[] args) {

        int temsilciSayisi = 5;
        musteriKuyrugu = new PriorityQueue<>();
        temsilciler = new Temsilci[temsilciSayisi];
        for (int i = 0; i < temsilciSayisi; i++) {
            temsilciler[i] = new Temsilci(i+1, "Temsilci " + (i+1));
        }
        gorusmeSayac = 0;
        String dosyaYolu = "Musteri.txt";



        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] veriler = satir.split("\\s+");
                int musteriID = Integer.parseInt(veriler[0].trim());
                String musteriAdi = veriler[1].trim();
                int uyelikDurumu = Integer.parseInt(veriler[2].trim());
                int cuzdanDurumu = Integer.parseInt(veriler[3].trim());
                int aylikHarcama = Integer.parseInt(veriler[4].trim());
                int yorumSayisi = Integer.parseInt(veriler[5].trim());
                int urunCesidi = Integer.parseInt(veriler[6].trim());
                int islemTuru = Integer.parseInt(veriler[7].trim());

                Musteri musteri = new Musteri(musteriID, musteriAdi, uyelikDurumu, cuzdanDurumu, aylikHarcama, yorumSayisi, urunCesidi, islemTuru);
                musteriler.add(musteri);
                gorusmeYap();
                musteriKayitEkle(musteri);

            }
        }   catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}