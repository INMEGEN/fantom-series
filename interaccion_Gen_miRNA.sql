SELECT miRNA_targets.weight , miRBase_pre.primary_name
FROM
Entrez_gene , miRNA_targets , miRBase_pre
where
miRBase_pre.feature_id = miRNA_targets.feature1_id
AND
mirNA_targets.feature2_id = Entrez_gene.feature_id
AND
Entrez_gene.feature_id= 'criterio/nombre para realizar la busqueda';
