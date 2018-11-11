#pretvori promise repozitorij
Set-Location .\promise
.\..\to_arff.ps1 -Filter "*_prepared.csv" -HeaderPath ".\..\header_promise.txt"
.\..\to_arff.ps1 -Filter "all.csv" -HeaderPath ".\..\header_promise.txt"

# pretvori bug inf
Set-Location ..
Set-Location .\bug-inf-usi
.\..\to_arff.ps1 -Filter "change-metrics_prepared.csv" -HeaderPath ".\..\header_bug_change.txt"
.\..\to_arff.ps1 -Filter "allchange-metrics.csv" -HeaderPath ".\..\header_bug_change.txt"

.\..\to_arff.ps1 -Filter "single-version-ck-oo_prepared.csv" -HeaderPath ".\..\header_bug_single.txt"
.\..\to_arff.ps1 -Filter "allsingle-version-ck-oo.csv" -HeaderPath ".\..\header_bug_single.txt"
Set-Location ..


