## Flashcards project development notes

### How to run

This project was initialized with `lein new re-frame flashcards2 +cider +test +compojure +garden +re-com +secretary`

It can be run from shell with `rlwrap lein figwheel dev` and then browsing to
[http://localhost:3449/](http://localhost:3449/)

Better, it can be run from Emacs:
- Beforehand, put this in Emacs config `(setq cider-cljs-lein-repl "(do (use
  'figwheel-sidecar.repl-api) (start-figwheel!) (cljs-repl))")`

- Open one of the .cljs files
- `C-c M-J` or `M-X cider-jack-in-clojurescript`
- Browse to [http://localhost:3449/](http://localhost:3449/)

#### Testing

`lein doo phantom test auto`

or `lein clean && lein doo phantom test once`, when appropriate.

This assumes that [phantomjs] (https://www.npmjs.com/package/phantomjs) is installed.

#### Generating CSS

Done at startup by lein script

Can be updated dynamically by `lein garden auto`. Requires refresh of browser.

