(ns pixelater.core
  (:import (javax.imageio ImageIO)
           (java.awt.image BufferedImage
                           Raster
                           WritableRaster)
           (java.io File))
  (:gen-class))

(defn img
  [s]
  (-> (File. s)
      (ImageIO/read)))

(defn raster
  [i]
  (.getData i))

(defn writable-raster
  [raster]
  (.createCompatibleWritableRaster raster))

(defn paste
  [wr sz y x pixel]
  (dorun
    (for [yd (range y (- (+ y sz) 1))
          xd (range x (- (+ x sz) 1))]
      (if (and (< yd (.getHeight wr)) (< xd (.getWidth wr)))
        (.setPixel wr xd yd pixel))))
    wr)

(defn pixelate
  [r wr]
  (let [sz 10]
    (dorun
     (for [y (range 0 (- (.getHeight r) 1) sz)
           x (range 0 (- (.getWidth r) 1) sz)]
       (paste wr sz y x (.getPixel r x y (double-array 3)))))
    wr))

(defn asis
  [r wr]
  (dorun
   (for [y (range 0 (- (.getHeight r) 1))
        x (range 0 (- (.getWidth r ) 1))]
     (.setPixel wr x y (.getPixel r x y (double-array 3)))))
    wr)

(defn write
  [i wr s]
      (println (map #(str %) (.getPixel wr 100 100 (double-array 3))))
      (.setData i wr)
      (ImageIO/write i "jpg" (File. s)))

(defn transform
  [source destination f]
  (let [i (img source)
        r (raster i)
        wr (writable-raster r)]
    (write i (f r wr) destination)))

(defn -main
  [& args]
  (transform "pic1.jpg" "pic2.jpg" asis)
  (transform "pic1.jpg" "pic7.jpg" pixelate))
