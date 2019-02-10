package com.example.thony.pharma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.aware.PublishConfig;

public class PharmaDataBase extends SQLiteOpenHelper
{

    private  ContentValues content;
    public static final String DATABASE_NAME = "pharma.db";

    public static final String PANIER = "PANIER";
    public static  final String ID_PANIER = "ID_PANIER";
    public static  final String ID_PRODUIT_PANIER = "ID_PRODUIT_PANIER";
    public static  final String NOM_PRODUIT_PANIER = "NOM_PRODUIT_PANIER";
    public static final String IMAGE_PRODUIT_PANIER = "IMAGE_PRODUIT_PANIER";
    public  static  final String PRIX_PRODUIT_PANIER = "PRIX_PRODUIT_PANIER";
    public static final  String QUANTITE_PRODUIT_PANIER = "QUANTITE_PRODUIT_PANIER";
    public  static  final  String IDENTIFIANT_CLIENT = "INDENTIFIANT_CLIENT";
    public static final String IDPHARMCY_OFMEDICAMENT = "IDPHARMCY_OFMEDICAMENT";
    public static final String CART_PHARMA_NAME = "CART_PHARMA_NAME";

    public  static  final String PERSONNE = "PERSONNE";

    public static  final  String ID_SESSION = "ID_SESSION";
    public static  final  String ID_PERSONNE = "ID_PERSONNE";
    public  static final  String EMAIL_PERSONNE ="EMAIL_PERSONNE";
    public static final  String PSEUDO_PERSONNE ="PSEUDO_PERSONNE";
    public static final String  PASSWORD_PERSONNE ="PASSWORD_PERSONNE";
    public static  final String PHOTO_PERSONNE ="PHOTO_PERSONNE";
    public static  final  String NUMERO_PERSONNE = "NUMERO_PERSONNE";
    public  static  final  String ROLE_PERSONNE = "ROLE_PERSONNE";
    public static  final String ACTIVATE_PERSONNE = "ACTIVATE_PERSONNE";


    public static String PHARMACY = "PHARMACY";
    public static final  String PHARMA_KEY = "PHARMA_KEY";
    public static final String ID_PHARMACIE = "ID_PHARMACIE";
    public static final String ID_PHARMACIEN = "ID_PHARMACIEN";
    public static final String NOM_PPHARMACIE = "NOM_PPHARMACIE";
    public static  final String DESCRIPTION_PHARMACIE = "DESCRIPTION_PHARMACIE";
    public static final String NUMEROS_PHARMACIE = "NUMEROS_PHARMACIE";
    public static  final String VILLE = "VILLE";
    public static final String PAY = "PAY";
    public  static final String ADRESSE = "ADRESSE";
    public static final String  PHARMA_PICTURE = "PHARMA_PICTURE";
    public static  final String GARDE = "GARDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String LATITUDE = "LATITUDE";

    public static  final String CREATE_PANIER_TABLE = "CREATE TABLE "+PANIER+"("+ID_PANIER+ " INTEGER PRIMARY KEY,"
            +ID_PRODUIT_PANIER+" INTEGER,"+IMAGE_PRODUIT_PANIER+" TEXT,"+PRIX_PRODUIT_PANIER+
            " DOUBLE,"+QUANTITE_PRODUIT_PANIER+" INTEGER,"+IDENTIFIANT_CLIENT+" INTEGER,"+NOM_PRODUIT_PANIER+" TEXT,"
            +IDPHARMCY_OFMEDICAMENT+" INTEGER,"+CART_PHARMA_NAME+" TEXT"+")";

    public static  final String CREATE_PERSONNE_TABLE = "CREATE TABLE "+PERSONNE+"("+ID_SESSION+" INTEGER PRIMARY KEY,"+ID_PERSONNE+" INTEGER,"+EMAIL_PERSONNE+" TEXT,"
            +PSEUDO_PERSONNE+" TEXT,"+PASSWORD_PERSONNE+" TEXT,"+PHOTO_PERSONNE+" TEXT,"+NUMERO_PERSONNE+" TEXT DEFAULT NULL,"
            +ROLE_PERSONNE+" INTEGER,"+ACTIVATE_PERSONNE+" INTEGER"+")";

   public static final String CREATE_PHARMACY_TABLE  = "CREATE TABLE "+PHARMACY+"("+PHARMA_KEY+" INTEGER,"+ID_PHARMACIE+" INTEGER,"
           +ID_PHARMACIEN+" INTEGER,"+NOM_PPHARMACIE+" TEXT,"+DESCRIPTION_PHARMACIE+" TEXT,"+NUMEROS_PHARMACIE
           +" TEXT,"+VILLE+" TEXT,"+PAY+" TEXT,"+ADRESSE+" TEXT,"+PHARMA_PICTURE+" TEXT,"+GARDE+" INTEGER,"+
           LONGITUDE+" DOUBLE,"+LATITUDE+" DOUBLE"+")";

    public PharmaDataBase(Context context)
    {
  super(context,DATABASE_NAME,null,1);
  //context.deleteDatabase(DATABASE_NAME);
    }




    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_PANIER_TABLE);
        db.execSQL(CREATE_PERSONNE_TABLE);
        db.execSQL(CREATE_PHARMACY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+PANIER);
        db.execSQL("DROP TABLE IF EXISTS "+PERSONNE);
        db.execSQL("DROP TABLE IF EXISTS "+PHARMACY);
        onCreate(db);
    }

    public Cursor getPersonneSession()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PERSONNE,null);
        return  res;
    }

    public  void emptyPersonneSession()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+PERSONNE);
        db.close();
    }

    public boolean verifyIfPersonneExist(int id_personne)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "select * from "+PERSONNE+" WHERE "+ID_PERSONNE+" = "+id_personne;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() <=0)
        {
            cursor.close();
            return  false;
        }
        else
        {
            cursor.close();
            return  true;
        }
    }

    public boolean insererPersonne(int id_personne,String email,String pseudo,String password,String photo,String number,int role,int activate)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        content = new ContentValues();

        content.put(ID_PERSONNE,id_personne);
        content.put(EMAIL_PERSONNE,email);
        content.put(PSEUDO_PERSONNE,pseudo);
        content.put(PASSWORD_PERSONNE,password);
        content.put(PHOTO_PERSONNE,photo);
        content.put(NUMERO_PERSONNE,number);
        content.put(ROLE_PERSONNE,role);
        content.put(ACTIVATE_PERSONNE,activate);
        long result = db.insert(PERSONNE,null,content);
        db.close();

        if(result == 1)
        {
      return  false;
        }
        else
        {
      return true;
        }
    }




    public boolean insererProduitPanier(int id_produit_panier,String nom_produit_panier,String image_produit_panier,double prix_produit_panier,int quantitte_produit_panier,int identifiant_client,int id_pharmacy,String pharrmaname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        content = new ContentValues();
        content.put(ID_PRODUIT_PANIER,id_produit_panier);
        content.put(NOM_PRODUIT_PANIER,nom_produit_panier);
        content.put(IMAGE_PRODUIT_PANIER,image_produit_panier);
        content.put(PRIX_PRODUIT_PANIER,prix_produit_panier);
        content.put(QUANTITE_PRODUIT_PANIER,quantitte_produit_panier);
        content.put(IDENTIFIANT_CLIENT,identifiant_client);
        content.put(IDPHARMCY_OFMEDICAMENT,id_pharmacy);
        content.put(CART_PHARMA_NAME,pharrmaname);
        long result = db.insert(PANIER,null,content);
        db.close();
        if(result==1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getMedicamentPanier()
    {
        SQLiteDatabase db=this.getWritableDatabase();

       Cursor res = db.rawQuery("select * from "+PANIER,null);

       return  res;
    }


    public void EmptyPanier()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+PANIER);
        db.close();
    }

    public Integer deleteMedicamentPanier(String id_panier)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(PANIER,"ID_PANIER = ? ",new String[]{id_panier});
    }

    public Integer updateQuantiteMedicamentPanier(int id_panier,int quantite)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        content = new ContentValues();
        content.put(QUANTITE_PRODUIT_PANIER,quantite);

        String selection = ID_PANIER+ " = "+id_panier;


       return db.update(PANIER,content,selection,null);
    }

    public long getNumberofMedicamentinPanier()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        long count = DatabaseUtils.queryNumEntries(db,PANIER);
        db.close();
      return count;
    }

    public  boolean verififMedicamentExist(String  nom_produit,int id_pharmacy)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        System.out.println("PANDATA:"+PANIER);
        System.out.println("DABBDSNMN"+CREATE_PANIER_TABLE);
        String query = "select * from "+PANIER+" where "+NOM_PRODUIT_PANIER+ " = ?"+" AND "+IDPHARMCY_OFMEDICAMENT+" = ?";


        Cursor cursor = db.rawQuery(query,new String[]{nom_produit,String.valueOf(id_pharmacy)});

        if(cursor.getCount() <=0)
        {
            cursor.close();
            return  false;
        }
        else
        {
            cursor.close();
            return  true;
        }

    }

public  boolean insererPharmacy(int pharmakey,int id_pharmacie,int id_pharmacien,String nom_pharmacie,String description,String numeros,
                                String ville,String pay,String adresse,String picture,int garde,double longitude,double latitude)
{
    SQLiteDatabase db=this.getWritableDatabase();
    content = new ContentValues();

    content.put(PHARMA_KEY,pharmakey);
    content.put(ID_PHARMACIE,id_pharmacie);
    content.put(ID_PHARMACIEN,id_pharmacien);
    content.put(NOM_PPHARMACIE,nom_pharmacie);
    content.put(DESCRIPTION_PHARMACIE,description);
    content.put(NUMEROS_PHARMACIE,numeros);
    content.put(VILLE,ville);
    content.put(PAY,pay);
    content.put(ADRESSE,adresse);
    content.put(PHARMA_PICTURE,picture);
    content.put(GARDE,garde);
    content.put(LONGITUDE,longitude);
    content.put(LATITUDE,latitude);
    long result = db.insert(PHARMACY,null,content);
    db.close();

    if(result == 1)
    {
        return false;
    }
    else
    {
        return true;
    }
}


public boolean verifyIfPharmacyExist(int pharmakey)
{
    SQLiteDatabase db=this.getWritableDatabase();
    String query = "select * from "+PHARMACY+" where "+PHARMA_KEY+" = "+pharmakey;

    Cursor cursor = db.rawQuery(query,null);

    if(cursor.getCount() == 0)
    {
        cursor.close();
        return  false;
    }
    else
    {
        return true;
    }

}

public Integer updatePharmacy(int pharmakey,int id_pharmacie,int id_pharmacien,String nom_pharmacie,String description,String numeros,
                              String ville,String pay,String adresse,String picture,int garde,double longitude,double latitude)
{

    SQLiteDatabase db=this.getWritableDatabase();
    content = new ContentValues();

    content.put(ID_PHARMACIE,id_pharmacie);
    content.put(ID_PHARMACIEN,id_pharmacien);
    content.put(NOM_PPHARMACIE,nom_pharmacie);
    content.put(DESCRIPTION_PHARMACIE,description);
    content.put(NUMEROS_PHARMACIE,numeros);
    content.put(VILLE,ville);
    content.put(PAY,pay);
    content.put(ADRESSE,adresse);
    content.put(PHARMA_PICTURE,picture);
    content.put(GARDE,garde);
    content.put(LONGITUDE,longitude);
    content.put(LATITUDE,latitude);

    String selection = PHARMA_KEY+ " = "+pharmakey;


    return db.update(PHARMACY,content,selection,null);
}


public Cursor AllPharamacy()
{
    SQLiteDatabase db=this.getWritableDatabase();
    Cursor res = db.rawQuery("SELECT * FROM "+PHARMACY,null);
  return res;
}


public Cursor GroupCartData()
{
    SQLiteDatabase db=this.getWritableDatabase();
    String query = "select * from "+PANIER+" GROUP BY "+IDPHARMCY_OFMEDICAMENT;
    Cursor res = db.rawQuery(query,null);
    return  res;
}

public Cursor getCustomerPharmaCart(int idpharmacy,int idclient)
{
    Cursor res;
    SQLiteDatabase db=this.getWritableDatabase();
    String query = "SELECT * FROM "+PANIER+" WHERE "+IDPHARMCY_OFMEDICAMENT+" = ?";
    System.out.println("requerycart"+query);
   // int id = 1;
    res = db.rawQuery(query,new String[]{String.valueOf(idpharmacy)});
    return res;
}


    @Override
    public String toString() {
        return "PharmaDataBase{" +
                "content=" + content +
                '}';
    }
}
