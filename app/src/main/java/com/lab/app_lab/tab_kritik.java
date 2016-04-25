package com.lab.app_lab;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class tab_kritik  extends Fragment  {
    Spinner lokasi;
    EditText txtkritik, gone_npm,txtjudul;
    ProgressDialog PD;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    Button btn_kritik,buttonChoose;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView img;
    Bitmap bitmap;
    File resultingFile;
    static final int REQUEST_TAKE_PHOTO = 11111;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_tab_kritik,container,false);
        img =(ImageView) view.findViewById(R.id.imgsrc);
        String list[]={"Pilih Lokasi","Lab Internet 1", "Lab Internet 2","Lab Aplikasi 1","Lab Aplikasi 2","Lab Aplikasi 3"};
        lokasi = (Spinner) view.findViewById(R.id.lokasi);
        ArrayAdapter<String> AdapterList = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,list);
        lokasi.setAdapter(AdapterList);
        gone_npm = (EditText) view.findViewById(R.id.gone_npm);
        txtkritik = (EditText) view.findViewById(R.id.txtkritik);
        txtjudul = (EditText) view.findViewById(R.id.txtjudul);
        btn_kritik = (Button) view.findViewById(R.id.btn_kritik);
        buttonChoose = (Button) view.findViewById(R.id.buttonChoose);
        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == buttonChoose){
                    showFileChooser();
                }
            }
        });

        sp = getActivity().getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String npm = sp.getString(config.EMAIL_SHARED_PREF, "Not Available");
        gone_npm.setText(npm);

        btn_kritik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String gon_npm = gone_npm.getText().toString();
                final String krt = txtkritik.getText().toString();
                final String lks = lokasi.getSelectedItem().toString();
                final String jdl = txtjudul.getText().toString();
                final String image_saran = getStringImage(bitmap);

                if (jdl.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(),"isi judul gambar terlebih dahulu",Toast.LENGTH_LONG).show();
                    return;
                }

                StringRequest postRequest = new StringRequest(Request.Method.POST, config.KRITIK_SARAN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getActivity(),
                                        "Data Inserted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                txtkritik.setText(null);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //Map<String, String> params = new HashMap<String, String>();
                        Map<String,String> params = new Hashtable<String, String>();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date();
                        ContentValues initialValues = new ContentValues();
                        initialValues.put("tanggal", dateFormat.format(date));
                        params.put(config.KEY_NPM_KRITIK,gon_npm);
                        params.put(config.KEY_LOKASI,lks);
                        params.put(config.KEY_GAMBAR_KRITIK,image_saran);
                        params.put(config.KEY_JUDUL_GAMBAR,jdl);
                        params.put(config.KEY_KRITIK,krt);
                        params.put(config.KEY_TGL_KRITIK, String.valueOf(initialValues));
                        return params;
                    }
                };

                // Adding request to request queue
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(postRequest);
                //MyApplication.getInstance().addToReqQueue(postRequest);

            }
        });


        return  view;
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //img.setImageBitmap(imageBitmap);
            bitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(bitmap);
        }
         else if (data != null) {
            Uri picturePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), picturePath);
            } catch (IOException e){}
            finally {
                img.setImageBitmap(bitmap);
            }

        } else {
            Toast.makeText(getActivity(), "Try Again!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void showFileChooser() {
        final CharSequence[] items = {"Ambil Foto", "Pilih dari Galeri",
                "Batal"};

        AlertDialog.Builder builder = new AlertDialog.Builder(tab_kritik.this.getActivity());
        builder.setTitle("Tambah Foto");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Ambil Foto")) {
                    Intent cameraIntent = new Intent(
                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(
                            cameraIntent,
                            REQUEST_TAKE_PHOTO);
                    /*File folder = new File(Environment.getExternalStorageDirectory().toString()+"/ImagesFolder/");
                    folder.mkdirs();

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    File resultingFile = new File(folder.toString() + "/image.jpg");
                    Uri uriSavedImage=Uri.fromFile(resultingFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

                    startActivityForResult(cameraIntent, 1888);*/
                    /*Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photo));
                    Uri imageUri = Uri.fromFile(photo);
                    tab_kritik.this.startActivityForResult(intent, 100);*/
                    /*Context context = getActivity();
                    PackageManager packageManager = context.getPackageManager();
                    if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false){
                        Toast.makeText(getActivity(), "This device does not have a camera.", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }

                    // Camera exists? Then proceed...
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    // Ensure that there's a camera activity to handle the intent
                    CameraActivity activity = (CameraActivity)getActivity();
                    if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                        // Create the File where the photo should go.
                        // If you don't do this, you may get a crash in some devices.
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            // Error occurred while creating the File
                            Toast toast = Toast.makeText(activity, "There was a problem saving the photo...", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        // Continue only if the File was successfully created
                        if (photoFile != null) {
                            Uri fileUri = Uri.fromFile(photoFile);
                            activity.setCapturedImageURI(fileUri);
                            activity.setCurrentPhotoPath(fileUri.getPath());
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    activity.getCapturedImageURI());
                            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                        }
                    }*/

                } else if (items[item].equals("Pilih dari Galeri")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                } else if (items[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void setFullImageFromFilePath(String imagePath, ImageView imageView) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    protected void addPhotoToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        CameraActivity activity = (CameraActivity)getActivity();
        File f = new File(activity.getCurrentPhotoPath());
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.getActivity().sendBroadcast(mediaScanIntent);
    }
}