package dk.sdu.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Manages game entities within the world
 */
public class World {
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    /**
     * Adds an entity to the world
     *
     * @param entity The entity to add
     * @return The ID of the added entity
     */
    public String addEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    /**
     * Removes an entity from the world by its ID
     *
     * @param entityID The ID of the entity to remove
     * @return The removed entity, or null if not found
     */
    public Entity removeEntity(String entityID) {
        return entityMap.remove(entityID);
    }

    /**
     * Removes an entity from the world
     *
     * @param entity The entity to remove
     * @return The removed entity, or null if not found
     */
    public Entity removeEntity(Entity entity) {
        if (entity == null) {
            return null;
        }
        return entityMap.remove(entity.getID());
    }

    /**
     * Gets all entities in the world
     *
     * @return An unmodifiable collection of all entities
     */
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    /**
     * Gets entities of specific types
     *
     * @param entityTypes Classes of entities to retrieve
     * @param <E> The entity type
     * @return List of matching entities
     */
    @SafeVarargs
    public final <E extends Entity> List<E> getEntities(Class<E>... entityTypes) {
        if (entityTypes == null || entityTypes.length == 0) {
            return new ArrayList<>();
        }

        return getEntities().stream()
                .filter(entity -> {
                    for (Class<E> type : entityTypes) {
                        if (type.isInstance(entity)) {
                            return true;
                        }
                    }
                    return false;
                })
                .map(entity -> {
                    // This cast is safe because of the isInstance check above
                    @SuppressWarnings("unchecked")
                    E e = (E) entity;
                    return e;
                })
                .collect(Collectors.toList());
    }

    /**
     * Gets an entity by its ID
     *
     * @param ID The ID of the entity to retrieve
     * @return The entity, or null if not found
     */
    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

    /**
     * Gets the count of entities in the world
     *
     * @return The number of entities
     */
    public int getEntityCount() {
        return entityMap.size();
    }

    /**
     * Checks if an entity exists in the world
     *
     * @param ID The ID of the entity to check
     * @return true if the entity exists, false otherwise
     */
    public boolean hasEntity(String ID) {
        return entityMap.containsKey(ID);
    }

    /**
     * Clears all entities from the world
     */
    public void clear() {
        entityMap.clear();
    }
}