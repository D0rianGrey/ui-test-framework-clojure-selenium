(ns ui-test-framework.core
  (:require [ui-test-framework.utils.driver :as driver]
            [ui-test-framework.config.config :as config]
            [clojure.tools.logging :as log])
  (:gen-class))

(defn simple-test
  "Простой тест для проверки работоспособности фреймворка"
  []
  (log/info "Запуск простого теста")
  (let [url (config/get-url :prod)]
    (log/info (str "Открытие URL: " url))
    (driver/navigate-to url)

    ;; Проверка заголовка страницы
    (let [title (.getTitle driver/*driver*)]
      (log/info (str "Заголовок страницы: " title))
      (println "Заголовок страницы:" title))

    ;; Дополнительная проверка наличия элемента на странице
    (try
      (let [login-button (.findElement driver/*driver*
                                       (org.openqa.selenium.By/id "login-button"))]
        (log/info "Кнопка логина найдена на странице")
        (println "Тест пройден успешно: кнопка логина найдена!"))
      (catch Exception e
        (log/error "Ошибка при поиске кнопки логина" e)
        (println "Тест не пройден: кнопка логина не найдена")))))

(defn -main
  "Точка входа в приложение"
  [& args]
  (println "Запуск тестового фреймворка...")
  (try
    (driver/with-driver :chrome
      (simple-test))
    (println "Тест завершен")
    (catch Exception e
      (log/error "Ошибка при выполнении теста" e)
      (println "Произошла ошибка при выполнении теста:" (.getMessage e))))
  (System/exit 0))