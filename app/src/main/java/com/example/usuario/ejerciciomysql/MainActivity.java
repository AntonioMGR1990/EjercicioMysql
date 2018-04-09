package com.example.usuario.ejerciciomysql;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import introduccionjdbc.LibroDAO;
import introduccionjdbc.datos.LibroDTO;

public class MainActivity extends AppCompatActivity {

    private ListView lvLibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLibros = findViewById(R.id.lvLibros);
        findViewById(R.id.boton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComunicationTask ct = new ComunicationTask();
                ct.execute();
            }
        });

    }
    private class ComunicationTask extends AsyncTask<Void,Void,List>{

        @Override
        protected List doInBackground(Void... voids) {

            /*
            String  url="jdbc:mysql://10.225.92.140:3306/libreria?useSSL=false";
            String sql="select id,ISBN,titulo,autor from libro";
            try {
                Connection conexion= DriverManager.getConnection(url,"test","cartagena2018");
                Statement stmt=conexion.createStatement();
                //  ResultSet result=stmt.executeQuery(sql);
                //	Connection conexion=DriverManager.getConnection(url,"root","#ff0016#");
                //	Statement stmt=conexion.createStatement();

                //	ResultSet result=stmt.executeQuery(sql);

                while(result.next()) {


                    publishProgress(result.getString(3));


                }*/

                //result.close(); etc..
            LibroDAO libroDAO = new LibroDAO();
                List<LibroDTO> libros = libroDAO.select();
            /*}catch (Exception e) {
                // TODO: handle exception
                System.err.println("no es posible connectar");
            }*/


            return libros;
        }

        /*@Override
        protected void onProgressUpdate(String... strings) {
            Toast.makeText(MainActivity.this,strings[0],Toast.LENGTH_SHORT).show();
        }*/

        @Override
        protected void onPostExecute(final List list) {
            ArrayAdapter<LibroDTO> arrayAdapter = new ArrayAdapter<LibroDTO>(MainActivity.this,android.R.layout.simple_list_item_2,android.R.id.text2,list){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                    LibroDTO libroDTO = (LibroDTO) list.get(position);
                    text1.setText(libroDTO.getTitulo());
                    text2.setText(libroDTO.getIsbn() + " " + libroDTO.getAutor());

                    return view;
                }
            };

            lvLibros.setAdapter(arrayAdapter);

        }
    }
}
