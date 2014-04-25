
function mascara(o, f) {
    v_obj = o;
    v_fun = f;
    setTimeout("execmascara()", 1);
}

function execmascara() {
    v_obj.value = v_fun(v_obj.value);
}

function formatarCNPJ(v) {
    v = v.replace(/\D/g, "");
    v = v.replace(/[0-9]{16}/, "inválido");
    v = v.replace(/(\d{1})(\d{12})$/, "$1.$2"); // coloca ponto antes dos  
    // ultimos 12 digitos  
    v = v.replace(/(\d{1})(\d{9})$/, "$1.$2"); // coloca ponto antes dos  
    // ultimos 9 digitos  
    v = v.replace(/(\d{1})(\d{6})$/, "$1/$2"); // coloca barra antes dos  
    // ultimos 6 digitos  
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1-$2"); // coloca traço antes dos  
    // ultimos 2 digitos  
    return v;
}

function formatarNumero(v) {
    v = v.replace(/\D/g,"");
    return v;
}

function formatarNumeroDuasCasas(v) {
    v = v.replace(/\D/g,"");
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2");
    return v;
}

function formatarNumeroTresCasas(v) {
    v = v.replace(/\D/g,"");
    v = v.replace(/(\d{1})(\d{1,3})$/, "$1,$2");
    return v;
}

function formatarKm(v) {
    v = v.replace(/\D/g,"");
    v = v.replace(/(\d{1})(\d{9})$/, "$1.$2");
    v = v.replace(/(\d{1})(\d{6})$/, "$1.$2");
    v = v.replace(/(\d{1})(\d{3})$/, "$1.$2");
    return v;
}

