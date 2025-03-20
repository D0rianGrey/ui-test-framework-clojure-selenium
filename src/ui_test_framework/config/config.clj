(ns ui-test-framework.config.config)

;; Базовые URL для различных окружений
(def base-urls
  {:prod "https://www.saucedemo.com"
   :stage "https://staging.saucedemo.com"
   :dev "https://dev.saucedemo.com"})

;; Тайм-ауты для различных операций (в секундах)
(def timeouts
  {:implicit-wait 10
   :page-load 30
   :script 30})

;; Учетные данные для авторизации
(def credentials
  {:standard {:username "standard_user" :password "secret_sauce"}
   :locked {:username "locked_out_user" :password "secret_sauce"}
   :problem {:username "problem_user" :password "secret_sauce"}
   :performance {:username "performance_glitch_user" :password "secret_sauce"}})

;; Настройки для отчетов Allure
(def allure-config
  {:results-dir "allure-results"
   :report-dir "allure-report"})

;; Получение URL в зависимости от окружения
(defn get-url
  [env]
  (get base-urls env (:prod base-urls)))