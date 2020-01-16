package repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

public abstract class DataRepository<T, ID extends Serializable> implements Serializable {

    protected Class<T> entityClass;

    Logger logger = Logger.getLogger(DataRepository.class.getName());

    @Inject
    protected EntityManager entityManager;

    public DataRepository() {}

    public DataRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T newInstance() {
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public List<T> findAll() {
        String qlString = "select e from " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(qlString).getResultList();
    }

    public T findById(ID id) {
        if (id == null) {
            return null;
        }
        return entityManager.find(entityClass, id);
    }

    public void update(T entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    public void remove(T entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }

    @AroundInvoke
    public Object log (InvocationContext ctx) throws Exception {
        String className = ctx.getTarget().getClass().getName();
        String methodName = ctx.getMethod().getName();
        String target = className + "." + methodName + "()";

        long t1 = System.currentTimeMillis();

        try {
            return ctx.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            long t2 = System.currentTimeMillis();
            logger.info(target + " took " + (t2 - t1) + "ms to execute.");
        }
    }
}
