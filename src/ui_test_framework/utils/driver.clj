(ns ui-test-framework.utils.driver
  (:require [clojure.tools.logging :as log])
  (:import [org.openqa.selenium WebDriver]
           [org.openqa.selenium.chrome ChromeDriver ChromeOptions]
           [org.openqa.selenium.firefox FirefoxDriver FirefoxOptions]
           [org.openqa.selenium.edge EdgeDriver EdgeOptions]
           [java.time Duration]))

(def ^:dynamic *driver* nil)

(defn create-chrome-driver
  "Создает и возвращает экземпляр ChromeDriver с настроенными опциями"
  []
  (log/info "Инициализация Chrome драйвера")
  (let [options (doto (ChromeOptions.)
                  (.addArguments ["--start-maximized"
                                  "--disable-infobars"
                                  "--disable-extensions"])
                  (.setImplicitWaitTimeout (Duration/ofSeconds 10)))]
    (ChromeDriver. options)))

(defn create-firefox-driver
  "Создает и возвращает экземпляр FirefoxDriver с настроенными опциями"
  []
  (log/info "Инициализация Firefox драйвера")
  (let [options (doto (FirefoxOptions.)
                  (.addArguments ["--start-maximized"])
                  (.setImplicitWaitTimeout (Duration/ofSeconds 10)))]
    (FirefoxDriver. options)))

(defn create-edge-driver
  "Создает и возвращает экземпляр EdgeDriver с настроенными опциями"
  []
  (log/info "Инициализация Edge драйвера")
  (let [options (doto (EdgeOptions.)
                  (.addArguments ["--start-maximized"])
                  (.setImplicitWaitTimeout (Duration/ofSeconds 10)))]
    (EdgeDriver. options)))

(defn create-driver
  "Создает и возвращает WebDriver в зависимости от указанного типа браузера"
  [browser-type]
  (case browser-type
    :chrome (create-chrome-driver)
    :firefox (create-firefox-driver)
    :edge (create-edge-driver)
    (do
      (log/warn (str "Неизвестный тип браузера: " browser-type ". Использую Chrome по умолчанию."))
      (create-chrome-driver))))

(defn init-driver
  "Инициализирует WebDriver и назначает его глобальной переменной *driver*"
  [browser-type]
  (alter-var-root #'*driver* (constantly (create-driver browser-type)))
  *driver*)

(defn quit-driver
  "Закрывает WebDriver и освобождает ресурсы"
  []
  (when *driver*
    (log/info "Закрытие WebDriver")
    (.quit *driver*)
    (alter-var-root #'*driver* (constantly nil))))

(defn navigate-to
  "Переходит по указанному URL"
  [url]
  (log/info (str "Переход по URL: " url))
  (.get *driver* url))

(defmacro with-driver
  "Макрос для работы с WebDriver в рамках выполнения блока кода"
  [browser-type & body]
  `(do
     (init-driver ~browser-type)
     (try
       ~@body
       (finally
         (quit-driver)))))
(ns ui-test-framework.utils.driver
  (:require [clojure.tools.logging :as log])
  (:import [org.openqa.selenium WebDriver]
           [org.openqa.selenium.chrome ChromeDriver ChromeOptions]
           [org.openqa.selenium.firefox FirefoxDriver FirefoxOptions]
           [org.openqa.selenium.edge EdgeDriver EdgeOptions]
           [java.time Duration]
           [io.github.bonigarcia.wdm WebDriverManager]))

;; И затем изменим функцию create-chrome-driver:
(defn create-chrome-driver
  "Создает и возвращает экземпляр ChromeDriver с настроенными опциями"
  []
  (log/info "Инициализация Chrome драйвера")
  (WebDriverManager/chromedriver)
  (.setup (WebDriverManager/chromedriver))
  (let [options (doto (ChromeOptions.)
                  (.addArguments ["--start-maximized"
                                  "--disable-infobars"
                                  "--disable-extensions"])
                  (.setImplicitWaitTimeout (Duration/ofSeconds 10)))]
    (ChromeDriver. options)))