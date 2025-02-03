# Meta-Land-Ticaret-Oyunu-Projesi

**Hazırlayanlar:**  
Fatma Nur Kurt, Tayyib Okur

---

## Projenin Amacı

Bu proje kapsamında, sanal ortamda arsa ve market ile ilgili (alım-satım, kiralama, işletme vb.) ticari aksiyonları yerine getiren bir platformun veritabanı sistemini tasarlamanız ve bu veritabanı üzerinde gerekli işlemleri gerçekleştiren bir oyunun geliştirilmesi amaçlanmaktadır.

---

## Oyun Tanımı ve Kuralları

### Kullanıcı Rolleri
- **Yönetici:**  
  - Tüm oyuncuların verilerini görüntüleyip kontrol edebilme yetkisine sahiptir.  
  - Oyun kurallarının sayısal parametrelerini belirler.
- **Oyuncu:**  
  - Oyunu oynayabilmek için sisteme giriş yapan kullanıcılardır.
  
### Oyun Ortamı
- Oyun, karesel alanlara bölünmüş bir grid (harita) üzerinde oynanır.  
  - Bu kareler, oyundaki arsa ve üzerinde kurulacak işletme alanlarına karşılık gelmektedir.  
  - Haritanın boyutu (örneğin, 3x3 veya 4x5) yönetici tarafından belirlenir.

### Başlangıç Koşulları
- Yeni oyunculara başlangıçta belirli miktarda para, yiyecek ve eşya verilir. (Bu değerler sayısal olarak gösterilir.)
- Günlük olarak, her oyuncunun sahip olduğu yiyecek, eşya ve para miktarından sabit miktarda eksiltme yapılır.  
  - Eğer yiyecek veya eşya miktarı tamamen tükenirse, oyuncu oyunu kaybeder.

### Çalışma ve İşlem Kuralları
- Her kullanıcı bir işletmede çalışma hakkına sahiptir.  
  - Çalışma saatleri ve günlük kazanacağı para miktarı, mağaza sahibiyle yapılan anlaşmaya göre belirlenir.  
  - Çalışma süresi boyunca oyuncu başka bir ticari işlem yapamaz.
  
- Oyunun başlangıcında tüm arsalar yöneticiye aittir.  
  - Yöneticiye ait sınırsız oyuncu kapasitesine sahip birer market, mağaza ve emlak bulunur.
  
- İşletmelerde çalışan oyuncuların günlük gelirleri veya eksiltmeleri, çalıştıkları alana göre değişiklik gösterir:  
  - Market çalışanı: Günlük yiyecek miktarı eksilmez.  
  - Mağaza çalışanı: Günlük eşya miktarı eksilmez.  
  - Emlak çalışanı: Günlük para miktarı eksilmez.
  
- Oyuncular, sahip oldukları parayla;  
  - Marketlerden yiyecek,  
  - Mağazalardan eşya veya  
  - Emlak noktalarından başka oyuncuya ait arsa/işletme (satın alma veya kiralama) işlemlerini gerçekleştirebilirler.
  - Emlakçı, belirlediği komisyon ücreti üzerinden alıcı-kiralayıcı veya satıcı-kiraya veren arasında aracılık yapar.
  
- **Arsa ve İşletme Kurma:**
  - Oyuncunun sıfırdan işletme kurabilmesi için öncelikle bir arsa sahibi olması gerekmektedir.  
  - Arsa üzerine, yönetici tarafından belirlenen para karşılığında market, mağaza veya emlak işletmesi kurulabilir.
  - Oyuncu aynı anda en fazla 2 arsaya sahip olabilir; bu sınırın aşılması durumunda sistem, yeni arsa alımına izin vermez.
  
- **İşletme Seviyeleri ve Kapasite:**
  - Her işletme, ilk kurulduğunda 1. seviye olarak başlar ve en fazla 3 oyuncu çalıştırabilir.  
  - Her seviye atlamasında işletmenin çalışma kapasitesi iki katına çıkar (1. seviye: 3, 2. seviye: 6, 3. seviye: 12 oyuncu vb.).
  - 1. seviyedeki işletmeler, ticari işlemlerden elde ettikleri gelirler dışında yönetici tarafından belirlenen sabit bir gelire sahiptir.  
    - Bu sabit gelir, her seviye geçişinde yönetici tarafından belirlenen oran üzerinden artırılır.
  - İşletme, bir hafta boyunca tam kapasite çalışırsa bir sonraki seviyeye geçer.
  
- **Demo ve Zaman Atlaması:**
  - Demo aşamasında, oyundaki diğer kullanıcıların işlem yapmadığı ve tüm parametrelerin sabit kaldığı varsayılarak, istenilen gün sayısı kadar tarih ileriye alınabilir.  
  - Böylece, oyun hızlandırılarak ileri tarih sonuçları görüntülenebilir.
  
- **Kullanıcı Bilgi Ekranı:**
  - Oyuncular, kendi yaptığı tüm işlemler (giderler, satın almalar, harcamalar, varlıklar, bütçe durumu, geçmiş tercih ve aksiyonlar) hakkında detaylı bilgiyi, açılır bir pencere üzerinden görüntüleyebilir.
  - Yönetici ise tüm oyuncuların bilgilerini detaylı şekilde izleyebilir.
  
- **Veritabanı Kullanımı:**
  - Tüm veriler ve veri hareketleri, veritabanında tutulur ve oyunun ilerleyişine göre güncellenir.  
  - Arayüzde gösterilen tüm bilgiler, veritabanı üzerinden yapılan sorgular ile elde edilir.
  
- **İşlem Geçmişi:**
  - Tasarlanan arayüzde, oyuncunun gerçekleştirdiği tüm işlemler; hem oyuncuya hem de işletmeye göre filtrelenip kronolojik sırayla gösterilecektir.

---

## Kurulum ve Çalıştırma

1. **Depoyu Klonlayın:**

   Projeyi yerel makinenize klonlamak için terminal veya Git arayüzünüz üzerinden aşağıdaki komutu kullanın:

   ```bash
   git clone https://github.com/Dawnfairy/Meta-Land-Ticaret-Oyunu-Projesi.git

2. **JDK Kurulumu:**

- Proje, JDK 17 kullanılarak geliştirilmiş ve test edilmiştir.
- Uygun bir JDK (JDK 17 veya daha güncel) yüklü olduğundan emin olun.
- JDK'yı aşağıdaki bağlantılardan indirebilirsiniz:
  - [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  - [OpenJDK 17](https://jdk.java.net/17/)
- JDK'yı yükledikten sonra, `JAVA_HOME` ortam değişkeninizi ayarlamayı unutmayın.

3. **Projeyi IDE'nizde Açın:**

    Tercih ettiğiniz Java geliştirme ortamında (örneğin, Eclipse, IntelliJ IDEA veya NetBeans) projeyi açın.

4. **Projeyi Çalıştırın:**

    Main.java dosyasını çalıştırarak projeyi başlatın.

---

## İletişim

Proje ile ilgili sorularınız veya katkı talepleriniz için lütfen aşağıdaki kişilerle iletişime geçin:

  Fatma Nur Kurt - kurtfatmanur8@gmail.com
  Tayyib Okur - ultratayyib@gmail.com

Bu proje, verilen gereksinimler doğrultusunda tasarlanmış ve iki farklı problem çözümünü içermektedir. Projeyi geliştirmek veya katkıda bulunmak isterseniz, lütfen pull request göndererek ya da issue oluşturarak bildirin.







