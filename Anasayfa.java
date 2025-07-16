package odev2;

import java.io.*;
import java.util.*;

public class Anasayfa {
    public static void main(String[] args) throws IOException {

        ArrayList<Kursiyer> kursiyerler = new ArrayList<Kursiyer>();
        ArrayList<Kurs> kurslar = new ArrayList<Kurs>();

        File file = new File("D:\\proje\\Java\\odev2\\src\\odev2\\kursiyer.txt");
        if (!file.exists()) {
            System.out.println(" dosya bulunamadı");
        }
        else
        {
            try(BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String satir;
                Kursiyer kursiyer = null;


            while ((satir = br.readLine()) != null){

                if (!satir.isEmpty() && satir.charAt(0) == '*') {
                    // Kursiyer bilgilerini al
                    String kursiyerBilgileri = satir.substring(1);
                    //System.out.println("Kursiyer Bilgileri: " + kursiyerBilgileri);
                    String[] arti = kursiyerBilgileri.split("\\+");
                    //5050+Ahmet Ada+23 parçalama
                    String Kursiyerno = arti[0];
                    String Kursiyerad = arti[1];
                    String Kursiyeryas = arti[2];
//                    System.out.println("no:" + Kursiyerno);
//                    System.out.println("ad:" + Kursiyerad);
//                    System.out.println("yas:" + Kursiyeryas);

                    kursiyer = new Kursiyer(Integer.parseInt(Kursiyerno),Kursiyerad,Integer.parseInt(Kursiyeryas));
                    kursiyerler.add(kursiyer);
                }

                // Kişinin kurslarını al
                else if (!satir.isEmpty() && satir.charAt(0) == '%') {
                    String kursBilgileri = satir.substring(1);
                    String[] bolme = kursBilgileri.split("\\+");
                    //System.out.println("Kurs Bilgileri: " + kursBilgileri);
                    String Kursno = bolme[0];
                    String Kursad = bolme[1];
                    //System.out.println("kurs no:" + Kursno);
                    //System.out.println("Kurs ad:" + Kursad);

                    Kurs kurs = new Kurs(Integer.parseInt(Kursno), Kursad);
                    kursiyer.getAlinankurslar().add(kurs);

                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // Kurs bilgilerini al
        File my_file= new File("D:\\proje\\Java\\odev2\\src\\odev2\\kurs.txt");
        if(!my_file.exists())
        {
            System.out.println(" dosya bulunamadı");
        }
        else
        {
            FileReader frr= new FileReader(my_file);
            BufferedReader brr= new BufferedReader(frr);
            String okunankurs;
            String [] okunankursdizi;

            while (null != (okunankurs = brr.readLine()))
            {
                okunankursdizi=okunankurs.split("\\+");

                if(okunankursdizi.length==2)
                {
                    kurslar.add(new Kurs(Integer.parseInt(okunankursdizi[0]),okunankursdizi[1]));
                }
            }
            frr.close();
            brr.close();

        }

        int secim;
        while(true)
        {
            System.out.println("""
                    \n \n
                    ********** MENU **********
                    1 – Kurs Ekle
                    2 – Kurs Listele
                    3 – Kurs Ara
                    4 – Kurs Sil
                    5 – Kursiyer Ekle
                    6 – Kursiyer Ara
                    7 – Kursiyer Sil
                    8 – Kursiyerleri Listele
                    9 – Kursiyerleri Ayrıntılı Listele
                    10 – Kursiyerin Ödeyeceği Tutarı Hesapla
                    11 – Çıkış""");
            System.out.println("İşlem seçiniz:");
            Scanner input= new Scanner(System.in);
            secim=input.nextInt();
            Anasayfa anasayfa= new Anasayfa();

            switch (secim)
            {
                case 1:
                  //1 – Kurs Ekle
                    anasayfa.KursEkle(kurslar);
                  break;
                case 2:
                    //2 – Kurs Listele
                    anasayfa.KursListele(kurslar);

                    break;
                case 3:
                    //3 – Kurs Ara
                    anasayfa.KursAra(kurslar);
                    break;
                case 4:
                    //4 – Kurs Sil
                    anasayfa.KursSil(kurslar,kursiyerler);
                    break;
                case 5:
                    //5 – Kursiyer Ekle
                    anasayfa.KursiyerEkle(kursiyerler,kurslar);
                    break;
                case 6:
                    //6 – Kursiyer Ara
                    anasayfa.KursiyerAra(kursiyerler);
                    break;
                case 7:
                    //7 – Kursiyer Sil
                    anasayfa.KursiyerSil(kursiyerler,kurslar);
                    break;
                case 8:
                    // 8 – Kursiyerleri Listele
                    anasayfa.KursiyerListele(kursiyerler);
                    break;
                case 9:
                    //  9 – Kursiyerleri Ayrıntılı Listele
                    anasayfa.KursiyerAyrintiliListele(kursiyerler);
                    break;
                case 10:
                    // 10 – Kursiyerin Ödeyeceği Tutarı Hesapla
                    System.out.println("kursiyer id:");
                    Scanner input1= new Scanner(System.in);
                    int kursiyer_id=input1.nextInt();
                    anasayfa.KursiyerinOdeyecegiTutarHesapla(kursiyer_id,kurslar,kursiyerler);
                    break;
                case 11:
                    // 11 – Çıkış
                    System.out.println("ÇIKIŞ YAPILIYOR!");
                    Cikis(kurslar,kursiyerler);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Yanlış giriş yaptınız!!");


            }

        }

    }

    public void KursEkle(ArrayList<Kurs> kurslar)
    {
        Scanner scan= new Scanner(System.in);
        System.out.println("Yeni kurs bilgileri giriniz. kursID:");
        int kursId=scan.nextInt();
        scan.nextLine();
        System.out.println("kursAd:");
        String kursAd=scan.nextLine();

        if (!kursIdVarMi(kursId,kurslar)) {
        // Kurs ID'si listede yoksa ekle
        kurslar.add(new Kurs(kursId, kursAd));
        System.out.println("Kurs eklendi.");
        } else {
        System.out.println("Bu kurs ID zaten var.");
        }
    }
    public boolean kursIdVarMi(int kursId, ArrayList<Kurs> kurslar) {
        for (Kurs kurs : kurslar) {
            if (kurs.getKursId() == kursId) {
                return true;
            }
        }
        return false;
    }

    public void KursListele(ArrayList<Kurs> kurslar) {
        System.out.println("Kurs ID:    Kurs Adı: ");
        for (Kurs kurs : kurslar) {
            System.out.println(kurs.getKursId() + "        " + kurs.getKursAd());
        }
    }

    public void KursAra(ArrayList<Kurs> kurslar)
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Aranacak kurs adı:");
        String kursAd=scan.nextLine();

        boolean kursBulundu = false;

        for (Kurs kurs : kurslar) {
            if (kurs.getKursAd().equalsIgnoreCase(kursAd)) {
                kursBulundu = true;
                System.out.println("Kurs bulundu. Kurs Detayları:");
                System.out.println("Kurs ID: " + kurs.getKursId());
                System.out.println("Kurs Adı: " + kurs.getKursAd());
            }
        }

        if (!kursBulundu) {
            System.out.println("Kurs bulunamadı.");
        }

    }

    public void KursSil(ArrayList<Kurs> kurslar, ArrayList<Kursiyer> kursiyerler)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Silinecek kurs adını giriniz:");
        String silinecekKursAd = scan.nextLine();

        boolean kursVarMi = false;

        // Silinecek kurs var mi kontrolü
        for(Kurs kurs: kurslar)
        {
            if(kurs.getKursAd().equals(silinecekKursAd))
            {
                kursVarMi=true;
                break;
            }
        }

        if(kursVarMi) {  // Kursu alan kursiyer var mı kontrolü
            for (Kursiyer kursiyer : kursiyerler) {
                ArrayList<Kurs> alinankurslar = kursiyer.getAlinankurslar();
                for (Kurs alinanKurs : alinankurslar) {
                    if (alinanKurs.getKursAd().equals(silinecekKursAd)) {
                        System.out.println("Bu kursu alan bir kursiyer bulunduğu için silme işlemi yapılamaz.");
                        return;
                    }
                }
            }
            // Kursu listeden sil
            for (Kurs kurs : kurslar) {
                if (kurs.getKursAd().equals(silinecekKursAd)) {
                    kurslar.remove(kurs);
                    System.out.println("Kurs silindi.");
                    return;
                }
            }
        }else{
            System.out.println("Belirtilen isimde bir kurs bulunamadı.");
        }

    }

    public void KursiyerEkle(ArrayList<Kursiyer>kursiyerler,ArrayList<Kurs> kurslar)
    {
        Scanner scan= new Scanner(System.in);
        System.out.println("Yeni kursiyer bilgileri giriniz. kursiyer ID:");
        int kursiyerId=scan.nextInt();
        scan.nextLine();

        System.out.println("Ad soyad");
        String kursiyerAdSoyad=scan.nextLine();

        System.out.println("yas");
        int kursiyerYas= scan.nextInt();
        scan.nextLine();

        if(!kursiyerIdVarMi(kursiyerId,kursiyerler))
        {
            kursiyerler.add(new Kursiyer(kursiyerId,kursiyerAdSoyad,kursiyerYas));
            //System.out.println("Kursiyer eklendi.");

            Anasayfa anasayfa1= new Anasayfa(); //alinan kursların id ve ad değerleri doğru girilmeli
            anasayfa1.KursListele(kurslar);    // bunun için önce listeledim

            System.out.println("alinan kurs sayisi giriniz:");
            int kursadet=scan.nextInt();
            scan.nextLine();

            System.out.println(" Lütfen kurs id ve adını doğru giriniz(büyük küçük harf dahi)");

            for(int i=0; i<kursadet; i++)
            {
                System.out.println((i + 1)+". kurs id:");
                int alinankursID= scan.nextInt();
                scan.nextLine();

                System.out.println((i + 1)+". kurs ad:");
                String alinankursAD= scan.nextLine();

                for (int k=0;k<kurslar.size();k++)
                {
                    if (kurslar.get(k).getKursId()==alinankursID && Objects.equals(kurslar.get(k).getKursAd(), alinankursAD))
                    {
                        Kurs kurs= new Kurs(kurslar.get(k).getKursId(),kurslar.get(k).getKursAd());
                        kursiyerler.get(kursiyerler.size() - 1).alinankurslar.add(kurs);
                    }

                }

            }
            System.out.println("Ekleme tamamlandı\n");

        }

    }
    public boolean kursiyerIdVarMi(int kursiyerId,ArrayList<Kursiyer>kursiyerler)
    {
        for (Kursiyer kursiyer: kursiyerler)
        {
            if(kursiyer.getKursiyerId()== kursiyerId)
            {
                return true;
            }
        }
        return false;
    }

    public void KursiyerAra(ArrayList<Kursiyer>kursiyerler)//sadece isimle konrol ediyo
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Aranacak kursiyer adı:");
        String kursiyerAd=scan.nextLine();

        boolean kursiyerBulundu = false;

        for (Kursiyer kursiyer : kursiyerler) {
            if (kursiyer.getKursiyerAdSoyad().equalsIgnoreCase(kursiyerAd)) {
                kursiyerBulundu = true;
                System.out.println("Kursiyer bulundu. Kursiyer Bilgileri:");
                System.out.println("Kursiyer ID: " + kursiyer.getKursiyerId());
                System.out.println("Kursiyer Adı Soyadı: " + kursiyer.getKursiyerAdSoyad());
                System.out.println("Kursiyer Yaşı: " + kursiyer.getKursiyerYas());

                // Kursiyerin aldığı kurslar
                ArrayList<Kurs> alinanKurslar = kursiyer.getAlinankurslar();
                if (!alinanKurslar.isEmpty()) {
                    System.out.println("Alınan Kurslar:");
                    for (Kurs alinanKurs : alinanKurslar) {
                        System.out.println("   Kurs ID: " + alinanKurs.getKursId() + ", Kurs Adı: " + alinanKurs.getKursAd());
                    }
                } else {
                    System.out.println("Bu kursiyer henüz herhangi bir kurs almamış.");
                }

                break;
            }
        }

        if (!kursiyerBulundu) {
            System.out.println("Belirtilen isimde bir kursiyer bulunamadı.");
        }
    }

    public void KursiyerSil(ArrayList<Kursiyer> kursiyerler,ArrayList<Kurs> kurslar)
    {
     Scanner scan= new Scanner(System.in);
     System.out.println("Silinecek kursiyer id:");
     int silinecekId=scan.nextInt();

     boolean kursiyerVarMi=false;
     for( Kursiyer kursiyer: kursiyerler){
         if(kursiyer.getKursiyerId()==silinecekId)
         {
             kursiyerVarMi=true;
             for (Kurs alinanKurs : kursiyer.getAlinankurslar()) {
                 kurslar.remove(alinanKurs);
             }
             break;
         }
     }

     if(kursiyerVarMi)
     {
         // Kursiyeri listeden sil
         kursiyerler.removeIf(k -> k.getKursiyerId() == silinecekId);
         System.out.println("Kursiyer ve aldığı kurslar silindi.");
     }
     else {
         System.out.println(silinecekId+"Bu ID'ye sahip kursiyer bulunamadı.");
     }

    }

    public void KursiyerListele(ArrayList<Kursiyer>kursiyerler)
    {
        System.out.println("Tüm Kursiyerler");
        for(Kursiyer kursiyer: kursiyerler)
        {
            System.out.println(kursiyer.getKursiyerId()+"  "+kursiyer.getKursiyerAdSoyad()+"  "+ kursiyer.getKursiyerYas());
        }
    }

    public void KursiyerAyrintiliListele(ArrayList<Kursiyer>kursiyerler)
    {
        System.out.println("Tüm Kursiyerler ve aldıkları kurslar");
        for(Kursiyer kursiyer: kursiyerler)
        {
            System.out.println(kursiyer.getKursiyerId()+"  "+kursiyer.getKursiyerAdSoyad()+"  "+ kursiyer.getKursiyerYas());
            for( Kurs alinankurs: kursiyer.getAlinankurslar())
            {
                System.out.println("      "+alinankurs.getKursId()+" "+alinankurs.getKursAd());
            }

            //aldıkları kursları da listeler
        }
    }

    public void KursiyerinOdeyecegiTutarHesapla(int kursiyer_id,ArrayList<Kurs>kurslar,ArrayList<Kursiyer>kursiyerler)
    {
        boolean kursiyerBulundu = false;
        for (Kursiyer kursiyer : kursiyerler) {
            if (kursiyer.getKursiyerId()==kursiyer_id) {
                kursiyerBulundu = true;

                // Kursiyerin aldığı kurslar
                ArrayList<Kurs> alinanKurslar = kursiyer.getAlinankurslar();
                if (!alinanKurslar.isEmpty()) {

                    int dersSyc=0;
                    for (int i=0; i<alinanKurslar.size();i++)
                    {
                        dersSyc++;
                    }
                    System.out.println("Alınan Kurs sayısı:"+dersSyc);
                    kursiyer.BorcHesapla(dersSyc);

                }
                break;
            }
        }
        if (!kursiyerBulundu) {
            System.out.println("Belirtilen id de bir kursiyer bulunamadı.");
        }

    }


    public static void Cikis(ArrayList<Kurs> kurslar, ArrayList<Kursiyer> kursiyerler) {
        try(BufferedWriter writer=new BufferedWriter( new FileWriter("D:\\proje\\Java\\odev2\\src\\odev2\\kursiyer.txt"));
            BufferedWriter writer1=new BufferedWriter(new FileWriter("D:\\proje\\Java\\odev2\\src\\odev2\\kurs.txt")))
        {

            for(Kursiyer kursiyer: kursiyerler)
            {
                // Alınan kurs sayısını bul
                int kursSayisi = kursiyer.getAlinankurslar().size();

                //kursiyer bilgilerini dosyaya yaz
                writer.write("*"+kursiyer.getKursiyerId()+"+"+kursiyer.getKursiyerAdSoyad()+"+"+kursiyer.getKursiyerYas()+"\n");


                // Alınan kursları dosyaya yaz
                for(int i=0; i<kursSayisi; i++)
                {
                    writer.write("%"+kursiyer.getAlinankurslar().get(i).getKursId()+"+"+kursiyer.getAlinankurslar().get(i).getKursAd()+"\n");
                }
            }

            for(Kurs kurs: kurslar)
            {
                writer1.write(kurs.getKursId()+"+"+kurs.getKursAd()+"\n");
            }

            System.out.println("dosyaya yazıldı");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}






