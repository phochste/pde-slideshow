import processing.core.*; 
import processing.xml.*; 

import processing.video.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class slideshow extends PApplet {



int vid_width = 200;
int vid_height = 200;
String title = "@hochstenbach";

String directory = "";
ArrayList images; 
int idx = 0;
boolean video = true;

Capture myCapture;
PFont font;


public void setup() {  
  
  directory = selectFolder();
  
  images = loadImages(directory);
  
  size(screen.width,screen.height);
  
  frame.setResizable(true);
  
  font = loadFont("GillSans-24.vlw");
  textFont(font);

  myCapture = new Capture(this, vid_width, vid_height, 30);
  
}

public void captureEvent(Capture myCapture) {
  myCapture.read();
}

public void draw() {
  background(0);
  
  if (images.size() > 0) {
    PImage img = (PImage) images.get(idx);
    image(img,0,0);
  }
  
  if (video) {
    image(myCapture, width - vid_width, height - vid_height);
    
    fill(255);
    stroke(255);
    rect(width - vid_width, height - 35, vid_width, 35);
    
    fill(0); 
    text("@hochstenbach",width - vid_width + 25,  height - 10);
  }
}

public void keyPressed() {
  println(key);
  
  if (key == 'n') {
    println("next");
    idx = idx < images.size() - 1 ? idx + 1 : idx;
  }
  else if (key == 'p') {
    println("prev");
    idx = idx != 0 ? idx - 1 : idx;
  }
  else if (key == 's') {
    idx = 0;
  }
  else if (key == 'e') {
    idx = images.size() - 1;
  }
  else if (key == 'v') {
    video = video ? false : true;
  }
}

public ArrayList loadImages(String directory) {
  File file = new File(directory);
  String[] names = new String[0];
  
  if (file.isDirectory()) {
      names = file.list();    
  }
  
  ArrayList images = new ArrayList();
  
  for (int i = 0 ; i < names.length ; i++) {
     if (! names[i].startsWith(".")) {
       images.add(loadImage(directory + "/" + names[i]));
     }
  }
  
  return images;
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "slideshow" });
  }
}
