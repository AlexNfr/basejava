import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];

    int size;

    void clear() {

        for (var i = 0; i < size; i++)
            storage[i] = null;

        size = 0;
    }

    void save(Resume resume) {

        if (resume == null || resume.uuid == null)
            return;

        var index = indexByUuid(resume.uuid);

        if (index < 0)
            if (size < 10_000)
                index = size++;
            else
                throw new RuntimeException("storage is full");

        storage[index] = resume;
    }

    Resume get(String uuid) {

        if (uuid == null)
            return null;

        var index = indexByUuid(uuid);

        return (index < 0 ? null : storage[index]);
    }

    void delete(String uuid) {

        if (uuid == null)
            return;

        var index = indexByUuid(uuid);

        if (index < 0)
            return;

        if (index != --size && size > 0) {

            storage[index] = storage[size];
            storage[size] = null;

        } else {
            storage[index] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {

        return size;
    }

    // Вспомогательные методы

    private int indexByUuid(String uuid) {

        if (uuid == null)
            return -1;

        for (var i = 0; i < size; i++)
            if (storage[i].uuid.equals(uuid))
                return i;

        return -1;
    }
}
