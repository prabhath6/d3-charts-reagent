(ns d3-charts-reagent.core
    (:require
      [reagent.core :as r]))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to D3 Charts"]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
