<?php
if( !defined( "DS" ) ) define( 'DS' , DIRECTORY_SEPARATOR );
define( "ROOT" , __DIR__ );
define( "FROOT" , ROOT . DS . "framework" );
define( "VIEW" , FROOT . DS . "view" );


include 'vendor/autoload.php';

$GLOBALS['m'] = $m = v('m') ? v('m') : 'resume';
$class = ucfirst( strtolower( $m ) );
$GLOBALS['a'] = $a = v('a') ? v('a') : 'index';

try
{
    $controller = "FangFrame\\Controller\\" .$m;
    call_user_func( [ new $controller() , $a ] );
}
catch(\Exception $e)
{
    die( $e->getMessage() );
}

// http://o.ftqq.com/_enter.php?m=user&a=login