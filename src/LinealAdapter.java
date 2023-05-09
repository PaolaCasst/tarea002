import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

/**
 * <p> Clase abstracta para modelar la estructura de datos pila y cola</p>
 * <p>Esta clase implementa una Cola genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.0
 * @param <T> Tipo que tienen los objetos que guarda esta cola.
 */
public abstract class LinealAdapter<T> extends Lista<T> {

		@Override
        public void  eliminar(T elemento){
	     		throw new UnsupportedOperationException("Operacion no valida");
        }
        /**
	     * Método para eliminar el elemento del inicio de la estructura.
	    */





        protected T eliminarInicio() throws NoSuchElementException{
            Nodo aux;
        	if(esVacia()){
                throw new NoSuchElementException("Operacion no valida");
            }else if(longitud == 1){
                aux = cabeza;
                cabeza = cola = null;
                longitud--;
                return aux.elemento;
            } else {
                aux = cabeza;
                cabeza.siguiente.anterior = null;
                cabeza = cabeza.siguiente;
                longitud--;
                return aux.elemento;
            }
        }

        protected Nodo ver() throws NoSuchElementException{
            if(cabeza != null){
                return cabeza;
            } else{
                throw new NoSuchElementException();
            }
        }




}
