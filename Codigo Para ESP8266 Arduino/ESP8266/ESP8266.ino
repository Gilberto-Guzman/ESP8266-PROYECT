//Librerias 

//Permite la utilizacion del sensor de temperatura y humedad
#include <DHT.h>

//Permite el enlace a una conexion de internet
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

//Permite en enlace a la conexion de nuestra base de datos
#include "FirebaseESP8266.h"



// Se abre el PIN 4, esto para conectar ahi nuestro sensor de temperatura y humedad
#define DHTPIN 4
#define DHTTYPE DHT11

// Agregamos aqui el nombre de nuestra red de internet
#define WIFI_SSID "UbeeC9D0-2.4G"
// Agregamos aqui la contraseña de nuestra red de internet
#define WIFI_PASSWORD "5F99F2C9D0"
 
// Agregamos la direccion de nuestro servidor
#define FIREBASE_HOST "base-de-datos-firebase-esp8266-default-rtdb.firebaseio.com"
// Agregamos la contraseña de nuestro servidor
#define FIREBASE_AUTH "4Kaa6urqHMXDXfNq7JfdJg7Zy1phZsuA871HyeYI"


WiFiClient client;
FirebaseData firebaseDataLed;
FirebaseData firebaseDataFan;
FirebaseData firebaseDataTemperature;
FirebaseData firebaseDataHumidity;

DHT dht(DHTPIN, DHTTYPE);

//Se abre el PIN 5 para darle corriente al Led
#define LED 5
//Se abre el PIN 12 para darle corriente al ventilador
#define FAN 12

void setup() {
  Serial.begin(9600);

  WiFi.begin (WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println ("");
  Serial.println ("Se conectó al wifi!");
  Serial.println(WiFi.localIP());

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

  pinMode(LED, OUTPUT);
  pinMode(FAN, OUTPUT);
  dht.begin();
}

void loop() {

  Firebase.getInt(firebaseDataLed,"/light_bulb");
  int datoled=firebaseDataLed.intData();
  analogWrite(LED, datoled);

  Serial.print(datoled);
  
  Firebase.getInt(firebaseDataFan,"/fan");
  int datofan=firebaseDataFan.intData();
  analogWrite(FAN, datofan);
  
  float t = dht.readTemperature();
  int h = dht.readHumidity(); 
     
  Firebase.setFloat(firebaseDataTemperature,"temperature", t);  
  Firebase.setInt(firebaseDataHumidity,"humidity", h);
}
