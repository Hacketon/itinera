
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

function formataTelefone(v){
    
    var r = v.replace(/\D/g,"");
    r = r.replace(/^0/,"");
    if (r.length > 10) {
        // 11+ digits. Format as 5+4.
        r = r.replace(/^(\d\d)(\d{5})(\d{4}).*/,"(0$1)$2-$3");
    }
    else if (r.length > 5) {
        // 6..10 digits. Format as 4+4
        r = r.replace(/^(\d\d)(\d{4})(\d{0,4}).*/,"(0$1)$2-$3");
    }
    else if (r.length > 2) {
        // 3..5 digits. Add (0XX..)
        r = r.replace(/^(\d\d)(\d{0,5})/,"(0$1)$2");
    }
    else {
        // 0..2 digits. Just add (0XX
        r = r.replace(/^(\d*)/, "(0$1");
    }
    return r;
}


function formatarCPF(v){
    v = v.replace(/\D/g, "");
    v = v.replace(/[0-9]{14}/, "inválido");
    //v = v.replace(/(\d{1})(\d{9})$/, "$1.$2"); // coloca ponto antes dos  
    // ultimos 12 digitos  
    v = v.replace(/(\d{1})(\d{8})$/, "$1.$2"); // coloca ponto antes dos  
    // ultimos 9 digitos  
    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2"); // coloca barra antes dos  
    // ultimos 6 digitos  
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1-$2"); // coloca traço antes dos  
    // ultimos 2 digitos  
    return v;
}