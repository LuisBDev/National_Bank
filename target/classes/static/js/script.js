async function htmlAjax(url, id) {
    try {
        const response = await fetch('./html-menu/' + url);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const html = await response.text();
        const container = document.getElementById(id);
        if (container) {
            container.innerHTML = html;  // Inserta el HTML
            
            // Procesa los scripts
            const scripts = Array.from(container.querySelectorAll('script'));
            for (const script of scripts) {
                const newScript = document.createElement('script');
                if (script.src) {
                    newScript.src = script.src;
                } else {
                    newScript.textContent = script.textContent;
                }
                document.body.appendChild(newScript);
                script.parentNode.removeChild(script);
            }

            // Procesa los estilos
            const styles = Array.from(container.querySelectorAll('style'));
            for (const style of styles) {
                const newStyle = document.createElement('style');
                newStyle.textContent = style.textContent;
                document.head.appendChild(newStyle);
                style.parentNode.removeChild(style);
            }
        } else {
            console.error('No se encontró el elemento con el ID:', id);
        }
    } catch (error) {
        console.error('Error al cargar el contenido:', error);
    }
}


