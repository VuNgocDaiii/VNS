function closeNav(){
    document.getElementById('mySidenav').style.width = '0px';
}

function openNav(){
    document.getElementById('mySidenav').style.width = '60%';
}

function closeLang(){
    document.getElementById('lang').style.display = 'none';
    document.getElementById('body').style.overflowY = 'unset';
}

function openLang(){
    document.getElementById('lang').style.display = 'block';
    document.getElementById('body').style.overflowY = 'hidden';
}