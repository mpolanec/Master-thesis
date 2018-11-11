param(
    [String]$Filter="*.arff", # filter
    [int] $metricsType = 1,
    [int] $dataSource = 1
)

$mTypeDecl = "MetricsType.CLASSIC_METRICS";
$dSourceDecl = "DataSource.PROMISE";

if($metricsType -ne 1){
    $mTypeDecl = "MetricsType.CHANGE_METRICS"
}
if($dataSource -ne 1){
    $dSourceDecl = "DataSource.BUG_INF"
}
Get-ChildItem -Filter $Filter -Recurse -File | ForEach-Object {
    #za izhod želimo:'         add(new DataFile("ant-1.3.csv", "ant", MetricsType.CLASSIC_METRICS, 1.0, DataSource.PROMISE));'
    $str = '         add(new DataFile("';
    $str += $_.Name + '", ';
    $str += '"' + $_.Directory.Name + '", ';
    $str += $mTypeDecl + ", ";
    $str += "1.0, ";
    $str += $dSourceDecl;
    $str += "));"
    Write-Output $str;
}
