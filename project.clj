(defproject ui-test-framework "0.1.0-SNAPSHOT"
  :description "UI Test Automation Framework with Clojure and Selenium"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.github.bonigarcia/webdrivermanager "5.5.3"] ; Для управления драйверами
                 [org.clojure/tools.logging "1.2.4"] ; Для логирования
                 [clj-webdriver "0.7.2"]  ; Обертка для Selenium на Clojure
                 [org.seleniumhq.selenium/selenium-java "4.13.0"]  ; Последняя версия Selenium
                 [ch.qos.logback/logback-classic "1.4.11"]  ; Для логирования
                 [io.qameta.allure/allure-java-commons "2.24.0"]  ; Базовая интеграция с Allure
                 [io.qameta.allure/allure-junit5 "2.24.0"]]  ; Адаптер Allure для JUnit5
  :plugins [[lein-shell "0.5.0"]]  ; Для запуска shell-команд из Leiningen
  :main ^:skip-aot ui-test-framework.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})