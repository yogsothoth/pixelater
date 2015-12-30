(defproject pixelater "0.0.1"
  :description "A simple picture pixelater, just for fun"
  :url "http://github.com/yogsothoth/picelater"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.nrepl "0.2.12"]]
  :plugins [[cider/cider-nrepl "0.10.0"]]
  :main ^:skip-aot pixelater.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
