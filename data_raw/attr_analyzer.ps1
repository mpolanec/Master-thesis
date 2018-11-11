param(
    [String]$Filter="*.csv" # filter
)
Write-Output "Zacetek analize"

$commonAttributes = New-Object System.Collections.ArrayList<string>
$i = 0

Get-ChildItem -Filter $Filter -Recurse -File | ForEach-Object {
    $i++
    [string]$firstLine = Get-Content $_.FullName -Head 1
    [string]$separator = ","
    if($firstLine.Contains(";"))
    {
        $separator = ";"
    }

    $attributes = $firstLine.Split($separator)

    foreach ($a in $attributes){
        $a = $a.Trim();
    }

    if($i -eq 1)
    {
        $commonAttributes.AddRange($attributes)
    }
    else
    {
        # find matches
        $toRemove = New-Object System.Collections.ArrayList
        for($j = 0; $j -lt $commonAttributes.Count; $j++)
        {
            if(!$attributes.Contains($commonAttributes[$j]))
            {
                $toRemove.Add($j)
            }
        }
        $toRemove.Reverse()
        foreach($t in $toRemove)
        {
            $commonAttributes.RemoveAt($t)
        }
    }
    Write-Output "| $($_.Directory.Name)/$($_.Name) | $($firstLine) |"
}

Write-Output ""
Write-Output "Skupnih atributov: $($commonAttributes.Count)"
Write-Output ""
Write-Output "Skupni atributi: $($commonAttributes -join ', ')"
