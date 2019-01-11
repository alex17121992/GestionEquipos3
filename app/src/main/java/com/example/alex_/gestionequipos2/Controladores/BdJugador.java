package com.example.alex_.gestionequipos2.Controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alex_.gestionequipos2.Modelos.Equipo;
import com.example.alex_.gestionequipos2.Modelos.Jugador;

import java.util.ArrayList;
import java.util.List;


public class  BdJugador extends SQLiteOpenHelper {

    private Equipo equip;
    private Jugador jug;

    /**
     * Constructor de la clase
     * @param context contexto de la aplicacion
     * @param nombre nombre de la DB
     * @param factory este valor se le pasa a null
     * @param version la version de la Bd  por defecto a 1
     */

    public BdJugador(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    /**
     * Metodo para crear las tablas de la bd
     * @param db Este objeto es para poder ejecutar la instruccion de execSQL que te permite ejecutar consultas en RAW normalmente para DDL
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //aquí creamos las tablas
        db.execSQL("CREATE TABLE IF NOT EXISTS EQUIPO(\n" +
                "   ID INTEGER CONSTRAINT PK_EQUIPO_ID PRIMARY KEY AUTOINCREMENT,\n" +
                "   NOMBRE VARCHAR(100) NOT NULL," +
                "   NUMJUGADORES NUMERIC(2) DEFAULT 0);");
        db.execSQL("CREATE TABLE IF NOT EXISTS JUGADOR(\n" +
                "   ID INTEGER CONSTRAINT PK_JUGADOR_ID PRIMARY KEY AUTOINCREMENT,\n" +
                "   NOMBRE VARCHAR(50) NOT NULL,\n" +
                "   APELLIDOS VARCHAR(100) NOT NULL,\n" +
                "   NOMBREDEPORT VARCHAR(100),\n" +
                "   EDAD NUMERIC(2),\n" +
                "   ALTURA NUMERIC(1,2),\n" +
                "   PESO NUMERIC(3,2),\n" +
                "   DEMARCACIONPRI VARCHAR(50),\n" +
                "   DEMARCACIONSEC VARCHAR(50),\n" +
                "   PIE VARCHAR(50),\n" +
                "   LESIONES VARCHAR(1),\n" +
                "   EQUIPOSPRO VARCHAR(100),\n" +
                "   FOTO BLOB," +
                "   TIPO VARCHAR(1) NOT NULL," +
                "   IDEQUIP INTEGER CONSTRAINT FK_JUG_EQUIP REFERENCES EQUIPO(ID))" +
                " ;");
        db.execSQL("CREATE TABLE IF NOT EXISTS ENTRENAMIENTO(" +
                "IDENT INTEGER CONSTRAINT PK_ENTR_ID PRIMARY KEY AUTOINCREMENT," +
                "ENTR BLOB" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS PARTIDOS(" +
                "FECHA VARCHAR(100) CONSTRAINT PK_PARTIDO_FECH PRIMARY KEY," +
                "TARJETASAM NUMERIC(2) DEFAULT 0,\n" +
                "TARJETASROJ NUMERIC(2) DEFAULT 0,\n" +
                "GOLES NUMERIC(2) DEFAULT 0,\n" +
                "CAMBIOS NUMERIC(2) DEFAULT 0,\n" +
                "EQUIPOLOC VARCHAR(100)," +
                "EQUIPOVIS VARCHAR(100)," +
                "RESULTLOC NUMERIC(2) DEFAULT 0," +
                "RESULTVISIT NUMERIC(2) DEFAULT 0," +
                "CERRADO VARCHAR(1) DEFAULT 'N');");
        db.execSQL("CREATE TABLE IF NOT EXISTS PART_JUG(" +
                "FECHA VARCHAR(100)," +
                "IDJUG INTEGER," +
                "TARJETASAM NUMERIC(2) DEFAULT 0,\n" +
                "TARJETASROJ NUMERIC(2),\n" +
                "GOLES NUMERIC(2),\n" +
                "MINGOL VARCHAR(10),\n" +
                "MINUTOCAMBIO VARCHAR(10)," +
                "MINTARJETROJ VARCHAR(10)," +
                "MINTARJETAM VARCHAR(10)," +
                "ASISTENCIA VARCHAR(50)," +
                "TITULAR VARCHAR(1)," +
                "PRIMARY KEY(FECHA,IDJUG)," +
                "FOREIGN KEY(FECHA) REFERENCES PARTIDO(FECHA)," +
                "FOREIGN KEY(IDJUG) REFERENCES JUGADOR(ID));");
        db.execSQL("CREATE TABLE IF NOT EXISTS EQUIP_PART(" +
                "FECHA VARCHAR(100)," +
                "ID INTEGER," +
                "PRIMARY KEY(FECHA,ID)," +
                "FOREIGN KEY(FECHA) REFERENCES PARTIDO(FECHA)," +
                "FOREIGN KEY(ID) REFERENCES EQUIPO(ID));");

        db.execSQL("CREATE TRIGGER IF NOT EXISTS TR_NUMJUG_EQUIPO AFTER " +
                " INSERT ON JUGADOR FOR EACH ROW " +
                " BEGIN " +
                "     UPDATE EQUIPO SET NUMJUGADORES=NUMJUGADORES+1 WHERE ID=NEW.IDEQUIP;" +
                " END;");

        db.execSQL("CREATE TRIGGER IF NOT EXISTS TR_NUMJUG_EQUIPO_DEL AFTER " +
                " DELETE ON JUGADOR FOR EACH ROW " +
                " BEGIN " +
                "     UPDATE EQUIPO SET NUMJUGADORES=NUMJUGADORES-1 WHERE ID=OLD.IDEQUIP;" +
                " END;");
        if(!existeScouting())
        db.execSQL("INSERT INTO EQUIPO(NOMBRE) VALUES('SCOUTING')");
    }

    /**
     * Este metodo sirver para actualizar la Bd y reconstruirla con los cambios nuevos
     * @param db Este objeto es para poder ejecutar la instruccion de execSQL que te permite ejecutar consultas en RAW normalmente para DDL
     * @param oldVersion version anterior de la DB
     * @param newVersion version nueva de ka DB
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS EQUIPO");
        db.execSQL("DROP TABLE IF EXISTS JUGADOR");
        db.execSQL("DROP TABLE IF EXISTS ENTRENAMIENTO");
        db.execSQL("DROP TABLE IF EXISTS PARTIDOS");
        db.execSQL("DROP TABLE IF EXISTS PART_JUG");
        db.execSQL("DROP TABLE IF EXISTS EQUIP_PART");
        onCreate(db);
    }

    /**
     * Metodo para insertar un equipo en la BD
     * @param e POJO de Equipo
     * @return devuelve el numero de registros afectados
     */

    public long insertarEquipo(Equipo e) {
        long nreg_afectados = -1;

        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            ContentValues valores = new ContentValues();

            valores.put("nombre", e.getNombre());
            nreg_afectados = db.insert("EQUIPO", null, valores);
        }
        db.close();
        return nreg_afectados;
    }

    /**
     * Metodo para insertar un jugador en la BD
     * @param j POJO de jugador
     * @return devuelte el numero de resgistros afectados
     */

    public long insertarJugador(Jugador j) {
        long nreg_afectados = -1;

        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            ContentValues valores = new ContentValues();

            valores.put("nombre", j.getNombre());
            valores.put("apellidos", j.getApellidos());
            valores.put("nombredeport",j.getNombreDeport());
            valores.put("edad", j.getEdad());
            valores.put("altura", j.getAltura());
            valores.put("peso", j.getPeso());
            valores.put("demarcacionpri", j.getDemarcPri());
            valores.put("demarcacionsec", j.getDemarcSec());
            valores.put("pie", j.getPie());
            valores.put("lesiones", j.getLesion());
            valores.put("equipospro", j.getEquipoPro());
            valores.put("foto",j.getImagen64());
            valores.put("tipo", j.getTipo());
            valores.put("idequip", j.getIdEquip());
            nreg_afectados = db.insert("JUGADOR", null, valores);
        }
        db.close();
        return nreg_afectados;
    }

    /**
     * Metodo para recorrer un cursor y crear un objeto del tipo equipo con los datos cargados
     * @return uu ArrayList de tipo equipo con todos los equipos
     */

    public List<Equipo> listadoEquipo() {
        /*  Abrimos  la  BD  de  Lectura  */
        SQLiteDatabase db = getReadableDatabase();
        /*  Creamos  una  Lista  de  Contactos  que  será  la  que  devolvamos  en  este   metodo  */
        List<Equipo> lista_equipo = new ArrayList<Equipo>();
        if (db != null) {
            String[] campos = {"id", "nombre","numjugadores"};
            /*  Como  queremos  devolver  todos  los  registros  el  tercer   parámetro  del  query  (  String  selection  )  es  null  */
            Cursor c = db.query("EQUIPO", campos, null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    equip = new Equipo(c.getInt(0), c.getString(1),c.getInt(2));
                    lista_equipo.add(equip);
                } while (c.moveToNext());
            }
            c.close();
        }
            db.close();
            return lista_equipo;
    }

    /**
     * Metodo para recorrer un cursor y cargar un objeto Jugador con los datos de la BD
     * @return devuelte un ArrayList de Jugadores
     */
    public List<Jugador> listadoJugador() {
        /*  Abrimos  la  BD  de  Lectura  */
        SQLiteDatabase db = getReadableDatabase();
        /*  Creamos  una  Lista  de  Contactos  que  será  la  que  devolvamos  en  este   metodo  */
        List<Jugador> lista_jug = new ArrayList<Jugador>();
        if (db != null) {
            String[] campos = {"ID", "NOMBRE","NOMBREDEPORT"};
            /*  Como  queremos  devolver  todos  los  registros  el  tercer   parámetro  del  query  (  String  selection  )  es  null  */
            Cursor c = db.query("JUGADOR", campos, "TIPO LIKE 'J'", null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    jug = new Jugador(c.getInt(0), c.getString(1),c.getString(2));
                    lista_jug.add(jug);
                } while (c.moveToNext());
            }
            c.close();
        }
        db.close();
        return lista_jug;
    }


    /**
     * Metodo para listar jugador de Scouting,es igual que listar un jugador normal con la diferencia que usa la clausula like para filtrar por
     * Scouting
     * @return devuelve un ArrayList de tipo jugador
     */
    public List<Jugador> listadoJugadorS() {
        /*  Abrimos  la  BD  de  Lectura  */
        SQLiteDatabase db = getReadableDatabase();
        /*  Creamos  una  Lista  de  Contactos  que  será  la  que  devolvamos  en  este   metodo  */
        List<Jugador> lista_jug = new ArrayList<Jugador>();
        if (db != null) {
            String[] campos = {"ID", "NOMBRE","NOMBREDEPORT"};
            /*  Como  queremos  devolver  todos  los  registros  el  tercer   parámetro  del  query  (  String  selection  )  es  null  */
            Cursor c = db.query("JUGADOR", campos, "TIPO LIKE 'S'", null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    jug = new Jugador(c.getInt(0), c.getString(1),c.getString(2));
                    lista_jug.add(jug);
                } while (c.moveToNext());
            }
            c.close();
        }
        db.close();
        return lista_jug;
    }

    /**
     * Metodo para editar un jugador
     * @param j objeto con todos los datos del jugadot
     * @return devuelde el numero de registros afectados
     */
    public long editarJugador(Jugador j){
        long nreg_afectados = -1;

        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {
            ContentValues valores = new ContentValues();

            valores.put("nombre", j.getNombre());
            valores.put("apellidos", j.getApellidos());
            valores.put("nombredeport",j.getNombreDeport());
            valores.put("edad", j.getEdad());
            valores.put("altura", j.getAltura());
            valores.put("peso", j.getPeso());
            valores.put("demarcacionpri", j.getDemarcPri());
            valores.put("demarcacionsec", j.getDemarcSec());
            valores.put("pie", j.getPie());
            valores.put("lesiones", j.getLesion());
            valores.put("equipospro", j.getEquipoPro());
            //valores.put("foto",j.getF());
            valores.put("tipo", j.getTipo());
            valores.put("idequip", j.getIdEquip());
            nreg_afectados=db.update("JUGADOR",valores, "id="+j.getId(),null);
        }
        db.close();
        return nreg_afectados;
    }

    /**
     * Metodo para borrar un jugador
     * @param id el id que es la clave primaria del jugador
     * @return devuelve el numero de registros afectados o -1 si no borrra nada
     */
    public long borrarJugador(int id){
        long nreg_afectados = -1;

        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {

            nreg_afectados=db.delete("JUGADOR", "id="+id,null);
        }
        db.close();
        return nreg_afectados;
    }

    /**
     * Metodo para borrar un equipo por su id
     * @param id
     * @return devuelve el numero de registros afectados
     */
    public long borrarEquipo(int id){
        long nreg_afectados = -1;

        SQLiteDatabase db = getWritableDatabase();

        if (db != null) {

            nreg_afectados=db.delete("EQUIPO", "id="+id,null);
        }
        db.close();
        return nreg_afectados;
    }

    /**
     * Metodo para mostrar los datos en la interfaz de editar un jugador
     * @param id
     * @return num_reg que es el numero de registros afectados
     */
    public Jugador listadoJugadorEd(int id) {
        /*  Abrimos  la  BD  de  Lectura  */
        SQLiteDatabase db = getReadableDatabase();
        /*  Creamos  una  Lista  de  Contactos  que  será  la  que  devolvamos  en  este   metodo  */
        if (db != null) {
            String[] campos = {"ID","NOMBRE","APELLIDOS","NOMBREDEPORT","edad","altura","peso","demarcacionpri","demarcacionsec","pie","lesiones","equipospro","tipo",
                    "idequip"};
            /*  Como  queremos  devolver  todos  los  registros  el  tercer   parámetro  del  query  (  String  selection  )  es  null  */
            Cursor c = db.query("JUGADOR", campos,"ID="+id, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    jug = new Jugador(c.getInt(0),c.getString(1),c.getString(2),
                            c.getString(3),c.getInt(4), c.getDouble(5),
                            c.getDouble(6),c.getString(7),c.getString(8),
                            c.getString(9),c.getInt(10),c.getString(11),
                            c.getString(12),c.getInt(13));
                } while (c.moveToNext());
            }
            c.close();
        }
        db.close();
        return jug;
    }

    /**
     * Metodo para saber si el equipo Scouting ha sido creado ya,para evitar crearlo mas de una vez
     * @return devuelte true si existe,false si no existe
     */
    public boolean existeScouting(){
        /*  Abrimos  la  BD  de  Lectura  */
        SQLiteDatabase db = getReadableDatabase();
        /*  Creamos  una  Lista  de  Contactos  que  será  la  que  devolvamos  en  este   metodo  */
        if (db != null) {
            String[] campos = {"nombre"};
            Cursor c = db.query("EQUIPO", campos, "NOMBRE LIKE '%SCOUTING%'", null, null, null, null, null);
            if (!c.moveToFirst())
                return false;
        }
        return true;
    }
}

