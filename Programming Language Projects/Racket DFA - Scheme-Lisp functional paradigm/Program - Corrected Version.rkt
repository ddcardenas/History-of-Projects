;; Daniel Cardenas
;; Comp333
;; Generalized DFA recognition: 
;;   This program is able to check if a given string
;;	 is in the language of a given DFA

#lang racket

;;define test-dfa
(define test-dfa
  '(a
    ( ((a 0)a) ((a 1)b) ((b 0)c) ((b 1)a) ((c 0)b) ((c 1)c) )
    (c)
    ))

;;define test-dfa2 ; can't end in 1
(define test-dfa2
  '(a
    ( ((a 0)a) ((a 1)b) ((b 0)a) ((b 1)b) )
    (a)
    ))

;; Transitions
(define (transition-state dfa-list state substring)
  (cond
    ((null? dfa-list) "error")
    (else
     (if
      (and ;; check if current input state and substring matches a transition scenario in dfa
       (equal? (car(car(car dfa-list))) state) ;; state from dfa and current input state  (car(car(car dfa-list)))
       (equal? (~a (car(cdr(car(car dfa-list))))) substring)) ;; substring from dfa and current input substring TESTING WITH CHANGES
       (car(cdr(car dfa-list))) ;; returns next state matches
       (transition-state (cdr dfa-list) state substring))))) ;; recursively calls next transition scenario

;; List of Transition Scenarios
(define (get-transition-list full-dfa-list)
  (car (cdr full-dfa-list)))


;; List of Final States
(define (get-final-states-list full-dfa-list)
  (car(cdr(cdr full-dfa-list))))

;; Final State
(define (is-final-state? final-states-list state)
  (cond
    ((null? final-states-list) #f)
    (else
     (if
      (equal? (car final-states-list) state)
      #t
      (is-final-state? (cdr final-states-list) state)))))

;;parse the string
(define (parse dfa str) (if(equal? str "") (is-empty-in-language? dfa) (is-in-the-language? dfa (get-transition-list dfa) (car dfa) str)))

;; checking if empty string is in language
(define (is-empty-in-language? dfa)
  (if(equal? (car dfa) (car(list-ref dfa 2))) "it is in the language" "not in the language"))

;; continue parsing
;;(define (parse dfa str)
;;    (is-in-the-language? dfa (get-transition-list dfa) (car dfa) str)) 

(define (is-in-the-language? dfa t-list state str)
  (cond
    ((and (= (string-length str) 1) (is-final-state? (get-final-states-list dfa) (transition-state t-list state str))) "it is in the language")
    ((and (= (string-length str) 1) (not(is-final-state? (get-final-states-list dfa) (transition-state t-list state str)))) "not in the language")
    (else (is-in-the-language? dfa t-list (transition-state t-list state (substring str 0 1)) (substring str 1)))
    ))



;; Testing test-dfa2, which must end in 0
(parse test-dfa2 "0")
(parse test-dfa2 "1")
(parse test-dfa2 "00")
(parse test-dfa2 "0011")
(parse test-dfa2 "00010")
;;Testing test-dfa
(parse test-dfa "10")
(parse test-dfa "0")
(parse test-dfa "0011010")
(parse test-dfa "0011010111")
(parse test-dfa "00110100")
(parse test-dfa "001101001")
(parse test-dfa "001101000")
;;Testing empty strings
"Now testing empty string"
(parse test-dfa2 "")
(parse test-dfa "")