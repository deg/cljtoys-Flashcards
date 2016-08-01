Project initialized with `lein new re-frame flashcards2 +cider +test +compojure +garden +re-com +secretary`

Can be run from shell with `rlwrap lein figwheel dev` and then browsing to
[http://localhost:3449/](http://localhost:3449/)

Better, can be run from Emacs:
- Open one of the .cljs files
- `C-c M-J` or M-X cider-jack-in-clojurescript
- That should be all that's needed, but I also had to run this in the repl: `(do (use 'figwheel-sidecar.repl-api) (start-figwheel!) (cljs-repl))`

For testing, compiling CSS, and production builds, see canned notes in
[README](../README.md). Will merge here later, once I understand them better.
