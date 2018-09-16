(ns d3-charts-reagent.viz
  (:require
   [reagent.core :as r]
   [d3-charts-reagent :as st]
   [cljsjs.d3]))


(defn viz-render
  "Renders viz"
  []
  (let [height (st/get-height)
        width (st/get-width)]
    [:div {:id "barchart"}
     [:svg {:width width
            :height height}]]))

(defn container-enter
  "Add container to the svg"
  []
  (-> js/d3
      (.select "#barchart svg")
      (. append "g")
      (.attr "class" "container")))

(defn container-did-mount []
  (container-enter))

(defn bars-enter []
  (let [data (clj->js (st/get-data))]
    (-> js/d3
        (.selectAll "#barchart svg .container .bars")
        (.selectAll "rect")
        (.data data)
        .enter
        (.append "rect"))))

(defn bars-exit []
  (let [data (clj->js (st/get-data))]
    (-> js/d3
        (.selectAll "#barchart svg .container .bars")
        (.selectAll "rect")
        (.data data)
        .exit
        .remove)))

(defn bars-update []
  (let [width   (st/get-width)
        height  (st/get-height)
        data    (clj->js (st/get-data))
        data-n  (count data)
        rect-height (/ height data-n)
        x-scale (-> js/d3
                    .scaleLinear
                    (.domain #js [0 5])
                    (.range #js [0 width]))]
    (-> js/d3
        (.select "#barchart svg .container .bars")
        (.selectAll "rect")
        (.data data)
        (.attr "fill" "green")
        (.attr "x" (fn []
                     (x-scale 0)))
        (.attr "y" (fn [d i]
                     (* i rect-height)))
        (.attr "height" (fn []
                          (- rect-height 1)))
        (.attr "width" (fn [d]
                         (x-scale (aget d "x"))))))))

(defn bars-did-update []
  (bars-enter)
  (bars-update)
  (bars-exit))

(defn bars-did-mount []
  (-> (js/d3.select "#barchart svg .container")
      (.append "g")
      (.attr "class" "bars"))
  (bars-did-update))

(defn viz-did-mount
  "Called when the viz component mount"
  []
  (container-did-mount)
  (bars-did-mount))

(defn viz-did-update []
  (bars-did-update))

(defn viz []
  (r/create-class
   {:reagent-render viz-render
    :component-did-mount viz-did-mount
    :component-did-update viz-did-update}))