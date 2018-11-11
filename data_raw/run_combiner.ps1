#pretvori promise repozitorij
Set-Location .\promise
.\..\promise_csv_combiner.ps1 -Filter "*.csv"

# pretvori bug inf
Set-Location ..
Set-Location .\bug-inf-usi
.\..\bug_inf_csv_combiner.ps1 -Filter "change-metrics.csv" -Mode 2
.\..\bug_inf_csv_combiner.ps1 -Filter "single-version-ck-oo.csv" -Mode 1
Set-Location ..

.\run_to_arff.ps1

