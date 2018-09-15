(ns d3-charts-reagent.prod
  (:require
    [d3-charts-reagent.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
