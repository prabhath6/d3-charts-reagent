(ns d3-charts-reagent
  (:require
   [reagent.core :as r]))

;; -------------------------
;; State
(defonce viz-dimension (r/atom {:width 300}))

(defonce app-state
  (r/atom {:data [{:x 1}
            {:x 2}
            {:x 3}]
           :width 300}))

;; -------------------------
;; State Helpers
(defn get-width []
  (:width @viz-dimension))

(defn get-height []
  (* 0.8 (get-width)))

(defn get-data []
  (:data @app-state))