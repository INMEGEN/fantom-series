create table miRNA_targets (feature1_id integer, feature2_id integer, weight float); 
create table ChIP_chip (feature1_id integer , feature2_id integer , weight float );
create table GoldStd_TF ( feature1_id integer , feature2_id integer , sym_value float );
create table L2_to_L3_april08 ( feature1_id integer , feature2_id integer );
create table L3_promoter_Entrez_08May16_EvN ( feature1_id integer , feature2_id integer );
create table miRNA_pre2mature ( feature1_id integer , feature2_id integer );
create table pre_miRNA_perturbation ( feature1_id integer , feature2_id integer , weight double );
create table PubmedID ( feature1_id integer , feature2_id integer , weight integer , sym_value integer );
create table siRNA_perturbation ( feature1_id integer , feature2_id integer , weight double );
create table TFmatrix_L2_may08 ( feature1_id integer , feature2_id integer , weight1 double , weight2 double );
create table CAGE_L1_promoter ( feature_id integer , primary_name varchar );
create table CAGE_L2_promoter_april2008 ( feature_id integer , primary_name varchar );
create table CAGE_L3_promoter_april2008 ( feature_id integer , primary_name varchar );
create table Entrez_gene ( feature_id integer ,	primary_name varchar , sym_value integer ) ;
create table miRBase_mature ( feature_id integer ,	primary_name varchar , sym_value varchar ) ;
create table miRBase_pre ( feature_id integer ,	primary_name varchar , sym_value varchar ) ;
