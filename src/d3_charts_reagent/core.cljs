(ns d3-charts-reagent.core
    (:require
      [reagent.core :as r]
      [d3-charts-reagent.viz :as v]))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to D3 Charts"]
   [v/viz]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
