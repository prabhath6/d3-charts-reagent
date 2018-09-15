(ns ^:figwheel-no-load d3-charts-reagent.dev
  (:require
    [d3-charts-reagent.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
