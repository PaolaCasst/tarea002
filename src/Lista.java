import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
/**
 * <p> Clase concreta para modelar la estructura de datos Lista</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 * @author Alejandro Hernández Mora <alejandrohmora@ciencias.unam.mx>
 * @version 1.1
 */
public class Lista<T> implements Listable<T>, Coleccionable<T>{

    /* Clase interna para construir la estructura */
    protected class Nodo{
    	/* Referencias a los nodos anterior y siguiente */
    	protected Nodo anterior, siguiente;
    	/* El elemento que almacena un nodo */
    	protected T elemento;

    	/* Unico constructor de la clase */
    	protected Nodo(T elemento){
    	    this.elemento = elemento;
    	}

      public boolean equals(Nodo n){
          if(n != null){
                if(n instanceof Nodo){
                    Nodo nodo = (Nodo)n;
                    return this.elemento.equals(nodo.elemento);
                }
            }
            return false;
      }
    }

    protected class IteradorLista implements Iterator{
        /* La lista a recorrer*/
        protected Lista<T> lista;
        /* Elementos del centinela que recorre la lista*/
        protected Lista<T>.Nodo anterior, siguiente;


        public IteradorLista(Lista<T> lista){
           siguiente = cabeza;
        }
        @Override
        public boolean hasNext() {
            return siguiente != null;

        }

        @Override
        public T next() {
            if(this.hasNext()){
                T temporal = siguiente.elemento;
                siguiente = siguiente.siguiente;
                return temporal;
            }
            return null;
        }

        @Override
        public void remove(){
	         throw new UnsupportedOperationException("Operacion no valida");    
        }
    }

    /* Atributos de la lista */
    protected Nodo cabeza, cola;
    protected int longitud;

    /**
     * Método que nos dice si las lista está vacía.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    public boolean esVacia(){
         return longitud == 0; 
    }
    /**
     * Método para eliminar todos los elementos de una lista
     */
    public void vaciar(){
        this.cabeza = this.cola = null;
        this.longitud = 0;
    }
    /**
     * Método para obtener el tamaño de la lista
     * @return tamanio Número de elementos de la lista.
     **/
    public int getTamanio(){
       return this.longitud;
    }
    /**
     * Método para agregar un elemento a la lista.
     * @param elemento Objeto que se agregará a la lista.
     */
    public void agregar(T elemento) throws IllegalArgumentException{
        if(elemento == null){
            throw new IllegalArgumentException("Argumento no valido"); 
        } else {
            agregarAlInicio(elemento);
        }

    }
        /**
     * Método para agregar al inicio un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlInicio(T elemento){
    	if(elemento == null)
            return;
        Nodo n = new Nodo(elemento);
        if(esVacia()){
            cabeza = cola = n;
        } else {
            cabeza.anterior = n;
            n.siguiente = cabeza;
            cabeza = n;
        }
        longitud++;
    }
    /**
     * Método para agregar al final un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlFinal(T elemento){
        if(elemento == null)
            return;
        Nodo n = new Nodo(elemento);
        if(esVacia()){
            cabeza = cola = n;
        } else {
            cola.siguiente = n;
            n.anterior = cola;
            cola = n;
        }
        longitud++;
    }
    /**
     * Método para verificar si un elemento pertenece a la lista.
     * @param elemento Objeto que se va a buscar en la lista.
     * @return <code>true</code> si el elemento esta en el lista y false en otro caso.
     */
    public boolean contiene(T elemento){
        for(T e : this){
            if(e.equals(elemento))
                return true;
        }
        return false;
    }
    /**
     * Método para eliminar un elemento de la lista.
     * @param elemento Objeto que se eliminara de la lista.
     */
    public void eliminar(T elemento) throws NoSuchElementException{
        Nodo aux = this.cabeza;
        while(aux != null){
            if(aux.elemento.equals(elemento)){
                if(longitud == 1){
                    cabeza = cola = null;
                } else if(aux == cabeza){
                    cabeza.siguiente.anterior = null;
                    cabeza = cabeza.siguiente;
                } else if (aux == cola){
                    cola.anterior.siguiente = null;
                    cola = cola.anterior;
                } else {
                    aux.siguiente.anterior = aux.anterior;
                    aux.anterior.siguiente = aux.siguiente;
                }
                longitud--;
                return;
            }
                aux = aux.siguiente;
        } 
        throw new NoSuchElementException();
    }

    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en ésta.
     */
    public int indiceDe(T elemento){
        int indice = 0;
        for(T e : this){
            if(elemento.equals(e))
                return indice;
            indice ++;
        }
        return -1;
    }

    /**
     * Método que nos dice en qué posición está un elemento en la lista
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista,
     * <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es < 0 o >longitud()
     */
    public T getElemento(int i)throws IndexOutOfBoundsException{
        java.util.Iterator it = this.iterator();
        T aux = null;
        if(i < longitud+1 && i > -1){
            while(i > -1){
                aux = (T) it.next();
                i--;
            }
            return aux;
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * @return Una copia con la lista l revés.
     */
    public Lista<T> reversa(){
        Lista reversa = new Lista<>();

        for(T e : this){
            reversa.agregarAlInicio(e);
        }

        return reversa;
    }

    /**
     * Método que devuelve una copi exacta de la lista
     * @return la copia de la lista.
     */
    public Lista<T> copia(){
        Lista copia = new Lista<>();

        for(T e : this){
            copia.agregarAlFinal(e);
        }

        return copia;
    }

    /**
     * Método que nos dice si una lista es igual que otra.
     * @param o objeto a comparar con la lista.
     * @return <code>true</code> si son iguales, <code>false</code> en otro caso.
     */
    @Override
    public boolean equals(Object o){
        Lista lista = (Lista<T>) o;
        int coincidencias = 0;
        java.util.Iterator it = lista.iterator();
        if(this.longitud == lista.longitud){
            for(T e : this){
                if(it.next().equals(e)){
                    coincidencias ++;
                }
            }
            return coincidencias == lista.longitud;
        } 
        return false;
    }

    /**
     * Método que devuelve un iterador sobre la lista
     * @return java.util.Iterador -- iterador sobre la lista
     */
    @Override
    public java.util.Iterator iterator(){
        return new IteradorLista(this);
    }

      /**
     * Método que devuelve una copia de la lista.
     * @param <T> Debe ser un tipo que extienda Comparable, para poder distinguir
     * el orden de los elementos en la lista.
     * @param l La lista de elementos comparables.
     * @return copia de la lista ordenada.
     */
    public static <T extends Comparable<T>> Lista <T> mergesort(Lista<T>l){
        if(l.longitud == 1 || l.longitud == 0){
            return l.copia();
        }
        Lista<T> l1 = new Lista<>();
        Lista<T> l2 = new Lista<>();
        int contador = 0;
        int nuevaLongitud = l.longitud/2;
        for(T e : l){
            if(contador < nuevaLongitud){
                l1.agregarAlFinal(e);
                contador++;
            } else {
                l2.agregarAlFinal(e);
                contador++;
            }
        }
        l1 = mergesort(l1);
        l2 = mergesort(l2);

        return merge(l1,l2);
    }

    public static <T extends Comparable<T>> Lista <T> merge(Lista<T> l1,Lista<T> l2){
       Lista <T> ordenados = new Lista<T>();
        java.util.Iterator it1 = l1.iterator();
        java.util.Iterator it2 = l2.iterator();
        T e1 = (T) it1.next();
        T e2 = (T) it2.next();
        while( e1 != null && e2 != null){
            if(e1.compareTo(e2) < 0){
                ordenados.agregarAlFinal(e1);
                e1 = (T) it1.next();
            } else {
                ordenados.agregarAlFinal(e2);
                e2 = (T) it2.next();
            }
        }

        while(e1 != null){
            ordenados.agregarAlFinal(e1);
                e1 = (T) it1.next();
        }
        while(e2 != null){
            ordenados.agregarAlFinal(e2);
                e2 = (T) it2.next();
        }

        return ordenados;
    }

    @Override
    public String toString(){
        String str = "";
        java.util.Iterator it = iterator();
        while(it.hasNext()){
            str = str + " |  " + it.next();
        }
        return str;
    }
}
