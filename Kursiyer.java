package odev2;

import java.util.ArrayList;

public class Kursiyer implements Hesaplama {
    private int kursiyerId;
    private String kursiyerAdSoyad;
    private int kursiyerYas;
    ArrayList<Kurs> alinankurslar = new ArrayList<Kurs>();

    public Kursiyer(int kursiyerId, String kursiyerAdSoyad, int kursiyerYas) {
        this.kursiyerId = kursiyerId;
        this.kursiyerAdSoyad = kursiyerAdSoyad;
        this.kursiyerYas = kursiyerYas;
    }

    public Kursiyer() {

    }

    @Override
    public void BorcHesapla() {

    }

    @Override
    public void BorcHesapla(int dersSyc) {
        double haftalikucret=0;
        if(dersSyc==1)
        {
            haftalikucret=500;
            System.out.println("Maalesef tek kurs alımlarda kampanyamız yoktur :(");
        }
        else if(dersSyc==2)   //Kampanya 1
        {
            haftalikucret=500+(500*0.8);
            System.out.println("Kampanya 1 den yararlandınız.Tebrikler!");

        } else if (dersSyc==3) {
            haftalikucret=1000+(500*0.75);
            System.out.println("Kampanya 2 den yararlandınız.Tebrikler!");

        } else if (dersSyc>3) {
            haftalikucret=dersSyc*(500*0.9);
            System.out.println("Kampanya 3 den yararlandınız.Tebrikler!");
        }
        else {
            System.out.println("Bu kursiyer henüz herhangi bir kurs almamış.");

        }
        System.out.println("Toplam aylık borcunuz:"+(4*haftalikucret));


    }

    public ArrayList<Kurs> getAlinankurslar() {
        return alinankurslar;
    }

    public void setAlinankurslar(ArrayList<Kurs> alinankurslar) {
        this.alinankurslar = alinankurslar;
    }

    public int getKursiyerId() {
        return kursiyerId;
    }

    public void setKursiyerId(int kursiyerId) {
        this.kursiyerId = kursiyerId;
    }

    public String getKursiyerAdSoyad() {
        return kursiyerAdSoyad;
    }

    public void setKursiyerAdSoyad(String kursiyerAdSoyad) {
        this.kursiyerAdSoyad = kursiyerAdSoyad;
    }

    public int getKursiyerYas() {
        return kursiyerYas;
    }

    public void setKursiyerYas(int kursiyerYas) {
        this.kursiyerYas = kursiyerYas;
    }


}
